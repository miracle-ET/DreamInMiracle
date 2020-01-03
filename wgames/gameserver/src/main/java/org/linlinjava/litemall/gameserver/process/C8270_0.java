package org.linlinjava.litemall.gameserver.process;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.json.JSONObject;
import org.linlinjava.litemall.db.domain.Pet;
import org.linlinjava.litemall.gameserver.GameHandler;
import org.linlinjava.litemall.gameserver.data.GameReadTool;
import org.linlinjava.litemall.gameserver.data.game.BasicAttributesUtils;
import org.linlinjava.litemall.gameserver.data.game.PetAndHelpSkillUtils;
import org.linlinjava.litemall.gameserver.data.game.PetAttributesUtils;
import org.linlinjava.litemall.gameserver.data.vo.*;
import org.linlinjava.litemall.gameserver.data.write.*;
import org.linlinjava.litemall.gameserver.domain.*;
import org.linlinjava.litemall.gameserver.game.GameData;
import org.linlinjava.litemall.gameserver.game.GameObjectChar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class C8270_0 implements GameHandler {

    @Override
    public void process(ChannelHandlerContext ctx, ByteBuf buff) {
    	//宠物标记
        int no = GameReadTool.readByte(buff);
        //妖石标记
        int pos = GameReadTool.readByte(buff);
        //操作标记
        String para = GameReadTool.readString(buff);
        //玩家属性
        Chara chara = GameObjectChar.getGameObjectChar().chara;


        //打入妖石
        //判断是不是打入操作
        if ("inset".equals(para)) {
        	//妖石
        	Goods g=null;
        	//宠物
        	Petbeibao p=null;
        	//遍历玩家背包
        	List<Goods> backpack = chara.backpack;
        	for (Goods goods : backpack) {
        		//如果是这个妖石
				if(goods.pos==pos) {
					g=goods;
				}
			}
        	List<Petbeibao> pets = chara.pets;
        	for (Petbeibao petbeibao : pets) {
        		if (petbeibao.no == no) {
        			p=petbeibao;
        		}
			}
        	if(g==null||p==null) {
        		return;
        	}
        	List<PetShuXing> px = p.petShuXing;
        	for (PetShuXing petShuXing : px) {
        		if(petShuXing.str.equals(g.goodsInfo.str)) {
        			Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                    vo_8165_0.msg = "不可重复打入！";
                    vo_8165_0.active = 0;
                    GameObjectChar.send(new M8165_0(), vo_8165_0);
                    return;
        		}
			}
        	if(g.goodsInfo.owner_id==0) {
        		return;
        	}
        	
        	g.goodsInfo.owner_id = g.goodsInfo.owner_id - 1;
            if (g.goodsInfo.owner_id == 0) {
                List<Goods> listbeibao = new ArrayList<>();
                Goods goods1 = new Goods();
                goods1.goodsBasics = null;
                goods1.goodsInfo = null;
                goods1.goodsLanSe = null;
                goods1.pos = g.pos;
                listbeibao.add(goods1);
                backpack.remove(g);
                GameObjectChar.send(new M65525_0(), listbeibao);
            }
            GameObjectChar.send(new M65525_0(), backpack);
            int weizhi = 12;
            //增肌一条属性
            weizhi = weizhi(weizhi, p.petShuXing);
            PetShuXing petShuXing = new PetShuXing();
            petShuXing.no = weizhi;
            petShuXing.type1 = 2;
            petShuXing.skill = g.goodsInfo.skill;
            petShuXing.str = g.goodsInfo.str;
            petShuXing.accurate = g.goodsLanSe.accurate;
            petShuXing.wiz = g.goodsLanSe.wiz;
            petShuXing.parry = g.goodsLanSe.parry;
            petShuXing.def = g.goodsLanSe.def;
            petShuXing.dex = g.goodsLanSe.dex;
            petShuXing.mana = g.goodsLanSe.mana;
            petShuXing.silver_coin = 8000;
            p.petShuXing.add(petShuXing);
            //修改总属性
            PetShuXing petShuXing1=p.petShuXing.get(0);
            petShuXing1.wiz += g.goodsLanSe.wiz;
            petShuXing1.parry += g.goodsLanSe.parry;
            petShuXing1.def += g.goodsLanSe.def;
            petShuXing1.dex += g.goodsLanSe.dex;
            petShuXing1.mana += g.goodsLanSe.mana;
            petShuXing1.accurate += g.goodsLanSe.accurate;
            
            
            List list = new ArrayList();
            list.add(p);
            GameObjectChar.send(new M65507_0(), list);

            Vo_8165_0 vo_8165_0 = new Vo_8165_0();
            vo_8165_0.msg = "打入妖石成功";
            vo_8165_0.active = 0;
            GameObjectChar.send(new M8165_0(), vo_8165_0);
            Vo_9129_0 vo_9129_0 = new Vo_9129_0();
            vo_9129_0.notify = 12000;
            vo_9129_0.para = "383174";
            GameObjectChar.send(new M9129_0(), vo_9129_0);
            
            /*for (int i = 0; i < backpack.size(); i++) {
                if (pos == backpack.get(i).pos) {
                    Goods goods = backpack.get(i);
                    //便利宠物背包
                    for (int j = 0; j < chara.pets.size(); j++) {
                        int weizhi = 12;
                        //petbeibao.no是宠物名
                        //petbeibao是宠物
                        Petbeibao petbeibao = chara.pets.get(j);
                        //如果是该宠物
                        if (petbeibao.no == no) {
                        	//遍历宠物的全属性列表
                            for (int k = 0; k < petbeibao.petShuXing.size(); k++) {
                            	//如果有相同的
                                if (petbeibao.petShuXing.get(k).str.equals(goods.goodsInfo.str)) {
                                    Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                                    vo_8165_0.msg = "不可重复打入！";
                                    vo_8165_0.active = 0;
                                    GameObjectChar.send(new M8165_0(), vo_8165_0);
                                    return;
                                }
                            }
                            //没有则增加
                            weizhi = weizhi(weizhi, petbeibao.petShuXing);
                            //增加一条属性
                            PetShuXing petShuXing = new PetShuXing();
                            petShuXing.no = weizhi;
                            petShuXing.type1 = 2;
                            petShuXing.skill = goods.goodsInfo.skill;
                            petShuXing.str = goods.goodsInfo.str;
                            petShuXing.accurate = goods.goodsLanSe.accurate;
                            petShuXing.wiz = goods.goodsLanSe.wiz;
                            petShuXing.parry = goods.goodsLanSe.parry;
                            petShuXing.def = goods.goodsLanSe.def;
                            petShuXing.dex = goods.goodsLanSe.dex;
                            petShuXing.mana = goods.goodsLanSe.mana;
                            petShuXing.silver_coin = 8000;
                            petbeibao.petShuXing.add(petShuXing);
                            for (int k = 0; k < petbeibao.petShuXing.size(); k++) {
                            	//no标识来标记宠物总属性
                                if (petbeibao.petShuXing.get(k).no == 0) {
                                    PetShuXing petShuXing1 = petbeibao.petShuXing.get(k);
                                    //总属性增加
                                    petShuXing1.wiz += goods.goodsLanSe.wiz;
                                    petShuXing1.parry += goods.goodsLanSe.parry;
                                    petShuXing1.def += goods.goodsLanSe.def;
                                    petShuXing1.dex += goods.goodsLanSe.dex;
                                    petShuXing1.mana += goods.goodsLanSe.mana;
                                    petShuXing1.accurate += goods.goodsLanSe.accurate;
                                }
                            }
                            
                            List list = new ArrayList();
                            list.add(petbeibao);
                            GameObjectChar.send(new M65507_0(), list);

                            Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                            vo_8165_0.msg = "打入妖石成功";
                            vo_8165_0.active = 0;
                            GameObjectChar.send(new M8165_0(), vo_8165_0);
                            Vo_9129_0 vo_9129_0 = new Vo_9129_0();
                            vo_9129_0.notify = 12000;
                            vo_9129_0.para = "383174";
                            GameObjectChar.send(new M9129_0(), vo_9129_0);
                        }
                    }


                    goods.goodsInfo.owner_id = goods.goodsInfo.owner_id - 1;
                    if (goods.goodsInfo.owner_id == 0) {
                        List<Goods> listbeibao = new ArrayList<>();
                        Goods goods1 = new Goods();
                        goods1.goodsBasics = null;
                        goods1.goodsInfo = null;
                        goods1.goodsLanSe = null;
                        goods1.pos = goods.pos;
                        listbeibao.add(goods1);
                        backpack.remove(goods);
                        GameObjectChar.send(new M65525_0(), listbeibao);
                    }
                    GameObjectChar.send(new M65525_0(), backpack);


                }
            }*/
        }

        if ("".equals(para)) {
            for (int i = 0; i < chara.backpack.size(); i++) {
                if (pos == chara.backpack.get(i).pos) {
                    Goods goods = chara.backpack.get(i);
                    if (chara.backpack.get(i).goodsInfo.str == "风灵丸") {
                    	if(!GameUtil.removemunber(chara, "风灵丸", 1)) {
                        	return;
                        }
                        
                        for (int j = 0; j < chara.pets.size(); j++) {
                            if (chara.pets.get(j).no == no) {


                            }
                        }
                    }

                    for (int j = 0; j < chara.pets.size(); j++) {
                        if (chara.pets.get(j).no == no) {
                            List list = new ArrayList();
                            list.add(chara.pets.get(j));
                            GameObjectChar.send(new M65507_0(), list);
                            boolean isfagong = chara.pets.get(j).petShuXing.get(0).rank > chara.pets.get(j).petShuXing.get(0).pet_mag_shape;
                            GameUtil.dujineng(1, chara.pets.get(j).petShuXing.get(0).metal, chara.pets.get(j).petShuXing.get(0).skill, isfagong, chara.pets.get(j).id, chara);
                            Vo_12023_0 vo_12023_0 = new Vo_12023_0();
                            vo_12023_0.owner_id = chara.id;
                            vo_12023_0.id = chara.pets.get(j).id;
                            vo_12023_0.god_book_skill_name = goods.goodsInfo.str;
                            vo_12023_0.god_book_skill_level = (int) (chara.level * 1.6);
                            vo_12023_0.god_book_skill_power = 6000;
                            vo_12023_0.god_book_skill_disabled = 0;
                            chara.pets.get(j).tianshu.add(vo_12023_0);
                            GameObjectChar.send(new M12023_0(), chara.pets.get(j).tianshu);


                            Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                            vo_20481_0.msg = "恭喜，你的宠物#Y" + chara.pets.get(j).petShuXing.get(0).str + "#n领悟了新的天书技能";
                            vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectChar.send(new M20481_0(), vo_20481_0);
                            if(!GameUtil.removemunber(chara, goods.goodsInfo.str, 1)) {
                            	return;
                            }
                            
                        }
                    }

                }
            }
        }


        //强化
        if ("mag".equals(para)) {
            for (int i = 0; i < chara.pets.size(); i++) {
                Petbeibao petbeibao = chara.pets.get(i);
                if (petbeibao.no == no) {
                    Pet pet = GameData.that.basePetService.findOneByName(petbeibao.petShuXing.get(0).str);
                    int[] ints = PetAttributesUtils.upgradePet(true, pet.getMagAttack(), petbeibao.petShuXing.get(0).raw_name, petbeibao.petShuXing.get(0).life_add_temp);
                    if (petbeibao.petShuXing.get(0).raw_name < ints[0]) {
                        petbeibao.petShuXing.get(0).pet_life_shape_temp += ints[1];
                        petbeibao.petShuXing.get(0).rank += ints[1];
                        petbeibao.petShuXing.get(0).life_add_temp = 0;
                        petbeibao.petShuXing.get(0).raw_name = ints[0];
                        Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                        vo_8165_0.msg = "恭喜强化成功！";
                        vo_8165_0.active = 0;
                        GameObjectChar.send(new M8165_0(), vo_8165_0);
                    } else {
                        petbeibao.petShuXing.get(0).life_add_temp = ints[2];
                        Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                        vo_8165_0.msg = "成长完成度增加了！";
                        vo_8165_0.active = 0;
                        GameObjectChar.send(new M8165_0(), vo_8165_0);
                    }
                    if(!GameUtil.removemunber(chara, "宠物强化丹", 1)) {
                    	return;
                    }
                    
                    List list = new ArrayList();
                    BasicAttributesUtils.petshuxing(petbeibao.petShuXing.get(0));
                    list.add(petbeibao);
//更新技能 没写； //32747   //12023

                    GameObjectChar.send(new M65507_0(), list);
                }
            }
        }
        if ("phy".equals(para)) {
            for (int i = 0; i < chara.pets.size(); i++) {
                Petbeibao petbeibao = chara.pets.get(i);
                if (petbeibao.no == no) {
                    Pet pet = GameData.that.basePetService.findOneByName(petbeibao.petShuXing.get(0).str);
                    int[] ints = PetAttributesUtils.upgradePet(false, pet.getPhyAttack(), petbeibao.petShuXing.get(0).mag_rebuild_level, petbeibao.petShuXing.get(0).mag_rebuild_rate);
                    //成功

                    if (petbeibao.petShuXing.get(0).mag_rebuild_level < ints[0]) {
                        petbeibao.petShuXing.get(0).mag_rebuild_add += ints[1];
                        petbeibao.petShuXing.get(0).pet_mag_shape += ints[1];
                        petbeibao.petShuXing.get(0).mag_rebuild_rate = 0;
                        petbeibao.petShuXing.get(0).mag_rebuild_level = ints[0];
                        Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                        vo_8165_0.msg = "恭喜强化成功！";
                        vo_8165_0.active = 0;
                        GameObjectChar.send(new M8165_0(), vo_8165_0);

                    } else {
                        //失败
                        petbeibao.petShuXing.get(0).mag_rebuild_rate = ints[2];
                        Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                        vo_8165_0.msg = "成长完成度增加了！";
                        vo_8165_0.active = 0;
                        GameObjectChar.send(new M8165_0(), vo_8165_0);
                    }
                    List list = new ArrayList();
                    BasicAttributesUtils.petshuxing(petbeibao.petShuXing.get(0));
                    list.add(petbeibao);

                    GameObjectChar.send(new M65507_0(), list);
                    if(!GameUtil.removemunber(chara, "宠物强化丹", 1)) {
                    	return;
                    }
                    
                }
            }
        }

        //宝宝洗练 refine  reset 野生洗练
//        洗练
        if ("reset".equals(para)) {
            for (int i = 0; i < chara.pets.size(); i++) {
                Petbeibao petbeibao = chara.pets.get(i);
                if (petbeibao.no == no) {
                    Pet pet = GameData.that.basePetService.findOneByName(petbeibao.petShuXing.get(0).str);
                    //洗野生
                    PetShuXing shuXing = petbeibao.petShuXing.get(0);
                    shuXing.penetrate = 2;
                    shuXing.skill = 1;
                    shuXing.pot = 0;
                    shuXing.resist_poison = 258;
                    shuXing.mana_effect = pet.getLife() - 40 - subtraction();
                    shuXing.attack_effect = pet.getMana() - 40 - subtraction();
                    shuXing.mag_effect = pet.getPhyAttack() - 40 - subtraction();
                    shuXing.phy_absorb = pet.getMagAttack() - 40 - subtraction();
                    shuXing.phy_effect = pet.getSpeed() - 40 - subtraction();
                    shuXing.pet_mana_shape = shuXing.mana_effect + 40;
                    shuXing.pet_speed_shape = shuXing.attack_effect + 40;
                    shuXing.pet_phy_shape = shuXing.phy_effect + 40;
                    shuXing.pet_mag_shape = shuXing.mag_effect + 40;
                    shuXing.rank = shuXing.phy_absorb + 40;
                    shuXing.phy_power = 1;
                    shuXing.mag_power = 1;
                    shuXing.life = 1;
                    shuXing.speed = 1;
                    shuXing.polar_point = 4;
                    shuXing.resist_point = shuXing.pet_mana_shape + shuXing.pet_speed_shape + shuXing.pet_phy_shape + shuXing.pet_mag_shape + shuXing.rank;
                    List list = new ArrayList();
                    BasicAttributesUtils.petshuxing(petbeibao.petShuXing.get(0));
                    shuXing.max_life = shuXing.def;
                    shuXing.max_mana = shuXing.dex;
                    list.add(petbeibao);
//更新技能 没写； //32747   //12023

                    boolean isfagong = shuXing.rank > shuXing.pet_mag_shape;

                    List<JiNeng> jiNengList = new ArrayList<>();
                    List<JSONObject> nomelSkills = PetAndHelpSkillUtils.getNomelSkills(1, shuXing.metal, 100, isfagong);
                    for (int j = 0; j < nomelSkills.size(); j++) {
                        JiNeng jiNeng = new JiNeng();
                        JSONObject jsonObject = nomelSkills.get(j);
                        jiNeng.id = petbeibao.id;
                        jiNeng.skill_no = Integer.parseInt((String) jsonObject.get("skillNo"));
                        jiNeng.skill_attrib = 0;
                        jiNeng.skill_level = 0;
                        jiNeng.level_improved = 0;
                        jiNeng.skill_mana_cost = 0;
                        jiNeng.skill_nimbus = 42949672;
                        jiNeng.skill_disabled = 0;
                        jiNeng.range = 0;
                        jiNeng.max_range = 0;
                        jiNengList.add(jiNeng);
                    }
                    List<Vo_32747_0> vo_32747_0List = GameUtil.a32747(jiNengList);
                    GameObjectChar.send(new M32747_0(), vo_32747_0List);


                    GameObjectChar.send(new M65507_0(), list);
                    Vo_40991_0 vo_40991_0 = new Vo_40991_0();
                    vo_40991_0.result = 0;
                    GameObjectChar.send(new M40991_0(), vo_40991_0);
                    Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                    vo_8165_0.msg = "洗练成功，宠物#Y" + pet.getName() + "(野生)#n已洗炼成为1级#Y" + pet.getName() + "(宝宝)#n";
                    vo_8165_0.active = 0;
                    GameObjectChar.send(new M8165_0(), vo_8165_0);
                    if(!GameUtil.removemunber(chara, "超级归元露", 1)) {
                    	return;
                    }
                    

                }
            }
        }
        //洗宝宝
        if ("refine".equals(para)) {
            for (int i = 0; i < chara.pets.size(); i++) {
                Petbeibao petbeibao = chara.pets.get(i);
                if (petbeibao.no == no) {
                    Pet pet = GameData.that.basePetService.findOneByName(petbeibao.petShuXing.get(0).str);
                    PetShuXing shuXing = petbeibao.petShuXing.get(0);

                    //洗炼  洗炼气血成长 洗炼后未点替换成长显示+
                    shuXing.pet_mana_shape_temp = pet.getLife() - subtraction() - shuXing.mana_effect - 40;
                    //洗炼  洗炼法力成长 洗炼后未点替换成长显示+
                    shuXing.pet_speed_shape_temp = pet.getMana() - subtraction() - shuXing.attack_effect - 40;
                    //洗炼  洗炼速度成长 洗炼后未点替换成长显示+

                    shuXing.pet_phy_shape_temp = pet.getSpeed() - subtraction() - shuXing.phy_effect - 40;
                    //洗炼  洗炼物攻成长 洗炼后未点替换成长显示+
                    shuXing.pet_mag_shape_temp = pet.getPhyAttack() - subtraction() - shuXing.mag_effect - 40;
                    //洗炼  洗炼法攻成长 洗炼后未点替换成长显示+
                    shuXing.evolve_degree = pet.getMagAttack() - subtraction() - shuXing.phy_absorb - 40;

//                    //洗练面板 血气成长93   客户端算法+40
//                    public int mana_effect=19;
//                    //洗炼面板   法力成长60  客户端算法+40
//                    public int attack_effect=1;
//                    //洗炼面板  速度成长48      客户端算法+40
//                    public int phy_effect;
//                    //洗炼面板  物攻成长6   65502+40=65542-65536=6
//                    public int mag_effect=16;
//                    //洗炼面板 法攻成长57    17+40
//                    public int phy_absorb=65504;

                    if (shuXing.mana_effect + 40 == pet.getLife()) {
                        shuXing.pet_mana_shape_temp = 0;
                    }
                    if (shuXing.attack_effect + 40 == pet.getMana()) {
                        shuXing.pet_speed_shape_temp = 0;
                    }
                    if (shuXing.phy_absorb + 40 == pet.getMagAttack()) {
                        shuXing.evolve_degree = 0;
                    }
                    if (shuXing.mag_effect + 40 == pet.getPhyAttack()) {
                        shuXing.pet_mag_shape_temp = 0;
                    }
                    if (shuXing.phy_effect + 40 == pet.getSpeed()) {
                        shuXing.pet_phy_shape_temp = 0;
                    }
                    List list = new ArrayList();
                    BasicAttributesUtils.petshuxing(petbeibao.petShuXing.get(0));

                    list.add(petbeibao);
//更新技能 没写； //32747   //12023
                    GameObjectChar.send(new M65507_0(), list);
                    Vo_40991_0 vo_40991_0 = new Vo_40991_0();
                    vo_40991_0.result = 0;
                    GameObjectChar.send(new M40991_0(), vo_40991_0);
                    Vo_8165_0 vo_8165_0 = new Vo_8165_0();
                    vo_8165_0.msg = "你的#Y" + pet.getName() + "#n经过洗炼，基础成长已重新生成。";
                    vo_8165_0.active = 0;
                    GameObjectChar.send(new M8165_0(), vo_8165_0);
                    if(!GameUtil.removemunber(chara, "超级归元露", 1)) {
                    	return;
                    }
                    
                }
            }

        }

        //强化
        if (57 == pos) {

        }


    }

    @Override
    public int cmd() {
        return 8270;
    }

    public int subtraction() {
        Random r = new Random();

        return r.nextInt(10);
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