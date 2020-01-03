package org.linlinjava.litemall.gameserver.process;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.linlinjava.litemall.gameserver.GameHandler;
import org.linlinjava.litemall.gameserver.data.game.BasicAttributesUtils;
import org.linlinjava.litemall.gameserver.data.vo.ListVo_65527_0;
import org.linlinjava.litemall.gameserver.data.vo.Vo_20481_0;
import org.linlinjava.litemall.gameserver.data.write.M20481_0;
import org.linlinjava.litemall.gameserver.data.write.M65527_0;
import org.linlinjava.litemall.gameserver.data.write.M65507_0;
import org.linlinjava.litemall.gameserver.domain.Chara;
import org.linlinjava.litemall.gameserver.domain.PetShuXing;
import org.linlinjava.litemall.gameserver.domain.Petbeibao;
import org.linlinjava.litemall.gameserver.game.GameObjectChar;
import org.springframework.stereotype.Service;
import org.linlinjava.litemall.gameserver.data.GameReadTool;

import java.util.ArrayList;
import java.util.List;

@Service
public class C8254_0 implements GameHandler {

    @Override
    public void process(ChannelHandlerContext ctx, ByteBuf buff) {

        int id = GameReadTool.readInt(buff);

        int type = GameReadTool.readByte(buff);

        int para1 = GameReadTool.readShort(buff);

        int para2 = GameReadTool.readShort(buff);

        int para3 = GameReadTool.readShort(buff);

        int para4 = GameReadTool.readShort(buff);

        int para5 = GameReadTool.readShort(buff);

        int para6 = GameReadTool.readShort(buff);

        Chara chara = GameObjectChar.getGameObjectChar().chara;

        if (para1 > 3000) {
            para1 = para1 - 65536;
        }
        if (para2 > 3000) {
            para2 = para2 - 65536;
        }
        if (para3 > 3000) {
            para3 = para3 - 65536;
        }
        if (para4 > 3000) {
            para4 = para4 - 65536;
        }
        if (para5 > 3000) {
            para5 = para5 - 65536;
        }

        int fen = 0;
        if (id == 0) {
            if (type == 1) {
                fen = 59;
            } else {
                fen = 164;
            }
        } else {
            fen = 36;
        }
        
        //para1-5是修改量
        
        //如果修改量全部>0 则直接修改属性
        if(para1>0&&para2>0&&para3>0&&para4>0&&para5>0) {
        	int count = para1 + para2 + para3 + para4 + para5;
        	if (id == 0) {
                if (type == 1) {
        			if(chara.polar_point - count<0) {
        				errInfo(1);
        				return;
        			}
                	chara.polar_point = chara.polar_point - count;
                    chara.life += para1;
                    chara.mag_power += para2;
                    chara.phy_power += para3;
                    chara.speed += para4;
                    
                } 
                else if (type == 2) {
                	if(chara.stamina - count<0) {
                		errInfo(2);
                		return;
                	}
                	chara.stamina = chara.stamina - count;
                    chara.resist_metal += para5;
                    chara.wood += para1;
                    chara.water += para2;
                    chara.fire += para3;
                    chara.earth += para4;
                    
                }
                ListVo_65527_0 vo_65527_0 = GameUtil.a65527(chara);
                GameObjectChar.send(new M65527_0(), vo_65527_0);

            } 
            else {
                for (int i = 0; i < chara.pets.size(); i++) {
                    Petbeibao petbeibao = chara.pets.get(i);
                    if (petbeibao.id == id) {
                        PetShuXing petShuXing = petbeibao.petShuXing.get(0);
                        count = para1 + para2 + para3 + para4;
                        if(petShuXing.polar_point - count<0) {
                        	errInfo(3);
                        	return;
                        }
                        petShuXing.polar_point = petShuXing.polar_point - count;
                        petShuXing.life += para1;
                        petShuXing.mag_power += para2;
                        petShuXing.phy_power += para3;
                        petShuXing.speed += para4;
                        BasicAttributesUtils.petshuxing(petShuXing);
                        List list = new ArrayList();
                        list.add(petbeibao);
                        GameObjectChar.send(new M65507_0(), list);
                    }
                }
            }
        }
        //如果有值小于0并且属性+para>level 偏移量不会让剩余属性变负  元宝足够则开始洗属性
        else {
        	int count = para1 + para2 + para3 + para4 + para5;
        	int level=chara.level;
        	//人物
        	if (id == 0) {
        		//普通属性
                if (type == 1) {
                	// 偏移量不会让剩余属性变负
        			if(chara.polar_point - count<0) {
        				errInfo(4);
        				return;
        			}
        			//属性+para>level
        			if(chara.life+para1<level||chara.mag_power+para2<level||chara.phy_power+para3<level||chara.speed+para4<level||chara.resist_metal+para5<level) {
        				errInfo(5);
        				return;
        			}
        			//元宝足够
        			if (chara.extra_life < count * (-fen)) {
                        Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                        vo_20481_0.msg = "元宝不足";
                        vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                        GameObjectChar.send(new M20481_0(), vo_20481_0);
                        return;
                    }
        			//扣除元宝
        			chara.extra_life = chara.extra_life - (-count) * fen;
        			//设置属性
                	chara.polar_point = chara.polar_point - count;
                    chara.life += para1;
                    chara.mag_power += para2;
                    chara.phy_power += para3;
                    chara.speed += para4;
                    
                } 
                //五行属性
                else if (type == 2) {
                	// 偏移量不会让剩余属性变负
                	if(chara.stamina - count<0) {
                		errInfo(6);
                		return;
                	}
        			//属性+para>level
        			if(chara.wood+para1<level||chara.water+para2<level||chara.fire+para3<level||chara.earth+para4<level||chara.resist_metal+para5<level) {
        				errInfo(7);
        				return;
        			}
        			//元宝足够
        			if (chara.extra_life < count * (-fen)) {
                        Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                        vo_20481_0.msg = "元宝不足";
                        vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                        GameObjectChar.send(new M20481_0(), vo_20481_0);
                        return;
                    }
        			//扣除元宝
        			chara.extra_life = chara.extra_life - (-count) * fen;
        			//设置属性
                	chara.stamina = chara.stamina - count;
                    chara.resist_metal += para5;
                    chara.wood += para1;
                    chara.water += para2;
                    chara.fire += para3;
                    chara.earth += para4;
                    
                }
                ListVo_65527_0 vo_65527_0 = GameUtil.a65527(chara);
                GameObjectChar.send(new M65527_0(), vo_65527_0);
            }
        	//宠物
        	else {
                for (int i = 0; i < chara.pets.size(); i++) {
                    Petbeibao petbeibao = chara.pets.get(i);
                    if (petbeibao.id == id) {
                    	
                        PetShuXing petShuXing = petbeibao.petShuXing.get(0);
                        // 偏移量不会让剩余属性变负
                        if(petShuXing.polar_point - count<0) {
                        	errInfo(8);
                        	return;
                        }
            			//属性+para>level
            			if(petShuXing.polar_point+para1<level||petShuXing.life+para2<level||petShuXing.mag_power+para3<level||petShuXing.phy_power+para4<level||petShuXing.speed+para5<level) {
            				errInfo(9);
            				return;
            			}
            			//元宝足够
            			if (chara.extra_life < count * (-fen)) {
                            Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                            vo_20481_0.msg = "元宝不足";
                            vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectChar.send(new M20481_0(), vo_20481_0);
                            return;
                        }
            			//扣除元宝
            			chara.extra_life = chara.extra_life - (-count) * fen;
                        petShuXing.polar_point = petShuXing.polar_point - count;
                        petShuXing.life += para1;
                        petShuXing.mag_power += para2;
                        petShuXing.phy_power += para3;
                        petShuXing.speed += para4;
                        BasicAttributesUtils.petshuxing(petShuXing);
                        List list = new ArrayList();
                        list.add(petbeibao);
                        GameObjectChar.send(new M65507_0(), list);
                    }
                }
                ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(chara);
                GameObjectChar.send(new M65527_0(), listVo_65527_0);
            }
        }
    }
    public void errInfo(int i) {
		Vo_20481_0 vo_20481_0=new Vo_20481_0();
		vo_20481_0.msg="出错啦 这个问题充钱就能解决"+i;
		vo_20481_0.time=1562987118;
		GameObjectChar.send(new M20481_0(), vo_20481_0);
    }
    @Override
    public int cmd() {
        return 8254;
    }
}