package org.linlinjava.litemall.gameserver.process;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.linlinjava.litemall.gameserver.GameHandler;
import org.linlinjava.litemall.gameserver.data.GameReadTool;
import org.linlinjava.litemall.gameserver.data.vo.Vo_20481_0;
import org.linlinjava.litemall.gameserver.data.write.M20481_0;
import org.linlinjava.litemall.gameserver.data.write.M65527_5;
import org.linlinjava.litemall.gameserver.data.write.M65507_0;
import org.linlinjava.litemall.gameserver.domain.Chara;
import org.linlinjava.litemall.gameserver.domain.PetShuXing;
import org.linlinjava.litemall.gameserver.game.GameObjectChar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class C53706_0 implements GameHandler {
    @Override
    public void process(ChannelHandlerContext ctx, ByteBuf buff) {

        int no = GameReadTool.readByte(buff);

        int num = GameReadTool.readShort(buff);

        int flag = GameReadTool.readByte(buff);

        Chara chara = GameObjectChar.getGameObjectChar().chara;
        if (flag == 1) {
            for (int i = 0; i < chara.pets.size(); i++) {
                if (chara.pets.get(i).no == no) {
                    if(!GameUtil.removemunber(chara, "超级神兽丹", num)) {
                    	return;
                    }

                    PetShuXing petShuXing = chara.pets.get(i).petShuXing.get(0);
                    //老的属性
                    int oldShape=petShuXing.shape;
                    //新的属性
                    int newShape=petShuXing.shape+2000* num;
                    int type=petShuXing.penetrate;
                    int upType=canUP(type, oldShape, newShape);
                    
                    
                    int weizhi = 15;
                    //增肌一条属性
                    weizhi = weizhi(weizhi, chara.pets.get(i).petShuXing);
                    PetShuXing newpx = new PetShuXing();
                    newpx.no = weizhi;
                    newpx.type1 = 2;
                    newpx.skill = 0;
                    newpx.str = "";
                    newpx.accurate = 0;
                    newpx.wiz = 0;
                    newpx.parry = 0;
                    newpx.def = 0;
                    newpx.dex = 0;
                    newpx.mana = 0;
                    newpx.silver_coin = 8000;
                    System.out.println(upType);
                    if(upType==1) {
                    	add(petShuXing, 6, 6, 10,newpx);
                    }
                    if(upType==2) {
                    	add(petShuXing, 4, 4, 5,newpx);
                    }
                    if(upType==3) {
                    	add(petShuXing, 5, 5, 5,newpx);
                    }
                    if(upType==4) {
                    	add(petShuXing, 5, 5, 5,newpx);
                    }
                    if(upType==5) {
                    	add(petShuXing, 5, 5, 5,newpx);
                    }
                    if(upType==6) {
                    	add(petShuXing, 5, 5, 5,newpx);
                    }
                    System.out.println("属性增加成功1");
                    petShuXing.shape = petShuXing.shape+2000 * num;
                    chara.pets.get(i).petShuXing.add(newpx);
                    System.out.println("属性增加成功2");
                    
                    List list = new ArrayList();
                    list.add(chara.pets.get(i));
                    GameObjectChar.send(new M65507_0(), list);
                    List list1 = new LinkedList();
                    list1.add(chara.pets.get(i).id);
                    list1.add(petShuXing.shape);
                    GameObjectChar.send(new M65527_5(), list1);
                    Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                    vo_20481_0.msg = "增加#R" + 2000 * num + "点#n亲密度。";
                    vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                    GameObjectChar.send(new M20481_0(), vo_20481_0);
                }
            }
        }
    }
    public void add(PetShuXing petShuXing,int a,int b,int c,PetShuXing newpx) {
    	int accurate=Math.round((float)(petShuXing.accurate*a*1.0/100));
    	int mana=Math.round((float)(petShuXing.mana*b*1.0/100));
    	int wiz=Math.round((float)(petShuXing.wiz*c*1.0/100));
    	//物伤
    	petShuXing.accurate=petShuXing.accurate+accurate;
    	//法伤
    	petShuXing.mana=petShuXing.mana+mana;
    	//防御
    	petShuXing.wiz=petShuXing.wiz+wiz;
    	newpx.accurate=newpx.accurate+accurate;
    	newpx.mana=newpx.mana+mana;
    	newpx.wiz=newpx.wiz+wiz;
    }
    //返回提升的档数
    public int canUP(int type,int oldShape,int newShape) {
    	//普通  精怪 2
    	//变异 3
    	//神兽 4
    	if(type==2) {
    		if(fun_1(oldShape, newShape, 10)) {
    			return 1;
    		}
    		if(fun_1(oldShape, newShape, 30)) {
    			return 2;
    		}
    		if(fun_1(oldShape, newShape, 50)) {
    			return 3;
    		}
    	}
    	if(type>2) {
    		if(fun_1(oldShape, newShape, 10)) {
    			return 1;
    		}
    		if(fun_1(oldShape, newShape, 30)) {
    			return 2;
    		}
    		if(fun_1(oldShape, newShape, 50)) {
    			return 3;
    		}
    		if(fun_1(oldShape, newShape, 100)) {
    			return 4;
    		}
    		if(fun_1(oldShape, newShape, 200)) {
    			return 5;
    		}
    		if(fun_1(oldShape, newShape, 500)) {
    			return 6;
    		}
    	}
    	return 0;

    }
    public boolean fun_1(int oldShape,int newShape,int a) {
    	a=a*10000;
    	if(newShape>=a&&oldShape<a) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    @Override
    public int cmd() {
        return 53706;
    }
    public int weizhi(int weizhi, List<PetShuXing> shuXings) {
        for (int i = 0; i < shuXings.size(); i++) {
            if (shuXings.get(i).no == weizhi) {
                weizhi++;
                weizhi(weizhi, shuXings);
            }
        }
        return weizhi;
    }
}