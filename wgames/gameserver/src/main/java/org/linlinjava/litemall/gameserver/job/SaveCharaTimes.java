package org.linlinjava.litemall.gameserver.job;

import org.linlinjava.litemall.db.domain.Notice;
import org.linlinjava.litemall.db.util.JSONUtils;
import org.linlinjava.litemall.gameserver.data.vo.ListVo_65527_0;
import org.linlinjava.litemall.gameserver.data.vo.Vo_16383_0;
import org.linlinjava.litemall.gameserver.data.vo.Vo_20481_0;
import org.linlinjava.litemall.gameserver.data.write.*;
import org.linlinjava.litemall.gameserver.domain.Chara;
import org.linlinjava.litemall.gameserver.fight.FightContainer;
import org.linlinjava.litemall.gameserver.fight.FightManager;
import org.linlinjava.litemall.gameserver.game.*;
import org.linlinjava.litemall.gameserver.process.GameUtil;
import org.linlinjava.litemall.gameserver.process.GameUtilRenWu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SaveCharaTimes {
    private long gonggaotim = System.currentTimeMillis();
    private static final Logger log = LoggerFactory.getLogger(SaveCharaTimes.class);

    @Scheduled(fixedDelay = 5000) 
    public void resetCompileTimes() {
        final List<GameObjectChar> all = GameObjectCharMng.getAll();
        for (GameObjectChar gameSession : all) {
            gameSession.characters.setData(JSONUtils.toJSONString(gameSession.chara));
            GameData.that.baseCharactersService.updateById(gameSession.characters);
        }
    }

    @Scheduled(fixedDelay = 10000) 
    public void onlines() {
        log.error("online  : " + GameObjectCharMng.getAll().size());
    }

    @Scheduled(fixedDelay = 2000) 
    public void autofight() {
        final List<FightContainer> listFight = FightManager.listFight;
        long time = System.currentTimeMillis();
        for (FightContainer fightContainer : listFight) {
            if (fightContainer.state.intValue() == 1 && fightContainer.roundTime + 1000 < time) {
                FightManager.doAutoSkill(fightContainer);
            }
            if (fightContainer.state.intValue() == 1 && fightContainer.roundTime + 24000 < time) {
                FightManager.doTimeupSkill(fightContainer);
            }
        }
    }


    @Scheduled(fixedRate = 2000) 
    public void autofightshuaguai() {
        long time = System.currentTimeMillis();
        if (GameLine.gameShuaGuai.shuaXing.size() < 8 && GameLine.gameShuaGuai.shuaXingTime + 180000 < time) {
            if (GameLine.gameShuaGuai.shuaXingzhuangtai == 0) {
                GameShuaGuai.sendYaoYan(GameLine.gameShuaGuai);
                GameLine.gameShuaGuai.shuaXingTime = System.currentTimeMillis();
                GameLine.gameShuaGuai.shuaXingzhuangtai = 1;
            }
        }
        if (GameShuaGuai.dengdaishuaXing.size() > 0 && GameLine.gameShuaGuai.shuaXingTime + 180000 < time) {
            if (GameLine.gameShuaGuai.shuaXingzhuangtai == 1) {
                GameShuaGuai.sendshuaguai(GameLine.gameShuaGuai);
                GameLine.gameShuaGuai.shuaXingTime = System.currentTimeMillis();
                GameLine.gameShuaGuai.shuaXingzhuangtai = 2;
            }
        }
        if (GameLine.gameShuaGuai.shuaXingzhuangtai == 2 && GameLine.gameShuaGuai.shuaXingTime + 180000 < time) {
            GameLine.gameShuaGuai.shuaXingzhuangtai = 0;
            GameLine.gameShuaGuai.shuaXingTime = System.currentTimeMillis();
            for (int i = 0; i < GameLine.gameShuaGuai.shuaXing.size(); i++) {
                GameLine.gameShuaGuai.shuaXing.get(i).wanjiaid = 0;

            }
        }
    }

    @Scheduled(fixedRate = 10000) 
    public void autofightshidao() {
        String[] shidaolevel = {"试道场(60-79)", "试道场(80-89)", "试道场(90-99)", "试道场(100-109)", "试道场(110-119)", "试道场(120-129)"};
        long time = System.currentTimeMillis();
        Date date = new Date();
        String xingqi = GameUtilRenWu.dateToWeekday(date);
        String msg = "";


        if (xingqi.equals("周日") && GameUtilRenWu.belongCalendarshidao()) {
            msg = "试道即将开启，请找试道申请员；";
            GameShiDao.sendyaoyan1(msg);
            GameShiDao.statTime = System.currentTimeMillis();
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMap(1, shidaolevel[i]);
                if (gameMap.gameShiDao.shuaXingzhuangtai == 0) {
                    gameMap.gameShiDao.shuaXingTime = System.currentTimeMillis();
                }
            }
            GameShiDao.statzhuangtai = 1;
            GameShiDao.statTime = System.currentTimeMillis();
            msg = "试道即将开启，请找试道申请员；";
            GameShiDao.sendyaoyan1(msg);
        }

        if (GameShiDao.statzhuangtai == 1 && GameShiDao.statTime + 600000 < time) { 
            msg = "试道大会已经开始";
            GameShiDao.sendyaoyan1(msg);
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMap(1, shidaolevel[i]);
                GameShiDao.sendYaoYan(gameMap.gameShiDao, gameMap);
                GameShiDao.sendshuaguai(gameMap.gameShiDao, gameMap);
                gameMap.gameShiDao.shuaXingTime = System.currentTimeMillis();
            }
            GameShiDao.statzhuangtai = 2;
            GameShiDao.statTime = System.currentTimeMillis();
        }

        if (GameShiDao.statzhuangtai == 2) {
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMap(1, shidaolevel[i]);
                if (gameMap.gameShiDao.shuaXingTime + 180000 < time) {
                    GameShiDao.sendYaoYan(gameMap.gameShiDao, gameMap);
                    GameShiDao.sendshuaguai(gameMap.gameShiDao, gameMap);
                    gameMap.gameShiDao.shuaXingTime = System.currentTimeMillis();
                    
                }
            }
        }
        if (GameShiDao.statzhuangtai == 2 && GameShiDao.statTime + 1800000 < time) {
            GameShiDao.statzhuangtai = 3;
            GameShiDao.statTime = System.currentTimeMillis();
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMapname(1, shidaolevel[i]);
                
                for (int j = 0; j < gameMap.gameShiDao.shidaoyuanmo.size(); j++) {
                    gameMap.send(new M12285_1(), gameMap.gameShiDao.shidaoyuanmo.get(j).id);
                }
                
                List<Chara> charas = new ArrayList<>();
                for (int j = 0; j < gameMap.sessionList.size(); j++) {

                    Chara chara = gameMap.sessionList.get(j).chara;




                    chara.balance = chara.balance - chara.level * 10000;
                    ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(chara);
                    GameObjectCharMng.getGameObjectChar(chara.id).sendOne(new M65527_0(), listVo_65527_0);
                    Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                    vo_20481_0.msg = "由于你在挑战元魔过程中取得了优异的成绩,因此获得了系统送出的" + chara.level * 10000 + "文钱的奖励。";
                    vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                    GameObjectCharMng.getGameObjectChar(chara.id).sendOne(new M20481_0(), vo_20481_0);
                    if (chara.shidaodaguaijifen >= 10) {
                        vo_20481_0 = new Vo_20481_0();
                        vo_20481_0.msg = "由于你所在队伍的挑战元魔的积分充足,现在进入参加试道大会的巅峰对决阶段。";
                        vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                        GameObjectCharMng.getGameObjectChar(chara.id).sendOne(new M20481_0(), vo_20481_0);
                    }
                }
                for (int k = 0; k < charas.size(); k++) {
                    GameUtilRenWu.shidaohuicheng(charas.get(k));
                    Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                    vo_20481_0.msg = "由于你所在队伍的挑战元魔的积分不足,无法参加试道大会的巅峰对\n" +
                            "决阶段。";
                    vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                    GameObjectCharMng.getGameObjectChar(charas.get(k).id).sendOne(new M20481_0(), vo_20481_0);
                }

                for (int j = 0; j < gameMap.sessionList.size(); j++) {
                    Chara chara = gameMap.sessionList.get(j).chara;
                    if (chara.shidaodaguaijifen > 30) {
                        chara.shidaodaguaijifen = 30;
                    }
                    chara.shidaocishu = chara.shidaodaguaijifen / 10;
                    chara.shidaodaguaijifen = 100;
                    Vo_20481_0 vo_20481_0 = new Vo_20481_0();
                    vo_20481_0.msg = "当前积分" + chara.shidaodaguaijifen;
                    vo_20481_0.time = (int) (System.currentTimeMillis() / 1000);
                    GameObjectCharMng.getGameObjectChar(chara.id).sendOne(new M20481_0(), vo_20481_0);
                }
            }
            GameShiDao.gonggaoTime = System.currentTimeMillis();
        }
        
        if (GameShiDao.statzhuangtai == 3 && GameShiDao.statTime + 9000000 < time) {

            GameShiDao.statzhuangtai = 0;


            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMapname(1, shidaolevel[i]);
                List<GameObjectChar> gameSessions = insertionSort(gameMap.sessionList);
                String mingname = "";
                int mingci = 0;
                List<Chara> charas = new ArrayList<>();
                for (int j = 0; j < gameSessions.size(); j++) {
                    charas.add(gameSessions.get(j).chara);
                    if (mingname.equals(gameSessions.get(j).gameTeam.duiwu.get(0).name + gameSessions.get(j).gameTeam.duiwu.get(0).shidaodaguaijifen)) {
                        continue;
                    }
                    mingname = gameSessions.get(j).gameTeam.duiwu.get(0).name + gameSessions.get(j).gameTeam.duiwu.get(0).shidaodaguaijifen;
                    mingci++;
                    if (mingci == 1) {
                        for (int k = 0; k < gameSessions.get(j).gameTeam.duiwu.size(); k++) {
                            gameSessions.get(j).gameTeam.duiwu.get(k).chenghao.put("试道王者", "试道王者");
                            GameUtil.chenghaoxiaoxi(gameSessions.get(j).gameTeam.duiwu.get(k));
                            Vo_20481_0 vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了#R试道王者#n的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            gameSessions.get(j).gameTeam.duiwu.get(k).extra_life = gameSessions.get(j).gameTeam.duiwu.get(k).extra_life + 200000;
                            vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了200000元宝的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(gameSessions.get(j).gameTeam.duiwu.get(k));
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M65527_0(), listVo_65527_0);
                        }
                    }
                    if (mingci == 2) {
                        for (int k = 0; k < gameSessions.get(j).gameTeam.duiwu.size(); k++) {
                            gameSessions.get(j).gameTeam.duiwu.get(k).chenghao.put("试道勇者", "试道勇者");
                            GameUtil.chenghaoxiaoxi(gameSessions.get(j).gameTeam.duiwu.get(k));
                            Vo_20481_0 vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了#R试道勇者#n的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            gameSessions.get(j).gameTeam.duiwu.get(k).extra_life = gameSessions.get(j).gameTeam.duiwu.get(k).extra_life + 100000;
                            vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了100000元宝的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(gameSessions.get(j).gameTeam.duiwu.get(k));
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M65527_0(), listVo_65527_0);
                        }
                    }
                    if (mingci == 3) {
                        for (int k = 0; k < gameSessions.get(j).gameTeam.duiwu.size(); k++) {
                            gameSessions.get(j).gameTeam.duiwu.get(k).chenghao.put("试道勇者", "试道勇者");
                            GameUtil.chenghaoxiaoxi(gameSessions.get(j).gameTeam.duiwu.get(k));
                            Vo_20481_0 vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了#R试道勇者#n的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            gameSessions.get(j).gameTeam.duiwu.get(k).extra_life = gameSessions.get(j).gameTeam.duiwu.get(k).extra_life + 50000;
                            vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了50000元宝的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(gameSessions.get(j).gameTeam.duiwu.get(k));
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M65527_0(), listVo_65527_0);
                        }
                    }
                }
                for (int k = 0; k < charas.size(); k++) {
                    GameUtilRenWu.shidaohuicheng(charas.get(k));
                }
            }

        }

        if (GameShiDao.statzhuangtai == 3 && GameShiDao.gonggaoTime + 30000 < time) {
            GameShiDao.gonggaoTime = System.currentTimeMillis();
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMapname(1, shidaolevel[i]);
                List<GameObjectChar> gameSessions = insertionSort(gameMap.sessionList);
                String mas = "";
                int size = 0;
                for (int j = 0; j < gameSessions.size(); j++) {
                    String str = "";
                    for (int k = 0; k < gameSessions.get(j).gameTeam.duiwu.size(); k++) {
                        str = gameSessions.get(j).gameTeam.duiwu.get(0).name + "的队伍积分" + gameSessions.get(j).gameTeam.duiwu.get(0).shidaodaguaijifen + "           ";
                        if (mas.contains(str) && mas.equals("")) {
                            str = "";
                            break;
                        }
                    }
                    if (!str.equals("")) {
                        size++;
                    }
                    if (size < 3) {

                        mas += str;
                    }
                }
                Vo_16383_0 vo_16383_0 = new Vo_16383_0();
                vo_16383_0.channel = 19;
                vo_16383_0.id = 0;
                vo_16383_0.name = "";
                vo_16383_0.msg = "#Y" + mas;
                long times = System.currentTimeMillis() / 1000;
                vo_16383_0.time = (int) times;
                vo_16383_0.privilege = 0;
                vo_16383_0.server_name = "涅槃重生22";
                vo_16383_0.show_extra = 2;
                vo_16383_0.compress = 0;
                vo_16383_0.orgLength = 65535;
                vo_16383_0.cardCount = 0;
                vo_16383_0.voiceTime = 0;
                vo_16383_0.token = "";
                vo_16383_0.checksum = 0;
                vo_16383_0.iid_str = "";
                vo_16383_0.has_break_lv_limit = 0;
                vo_16383_0.skill = 1;
                vo_16383_0.type = 1;

                gameMap.send(new M16383_0(), vo_16383_0);
            }
            for (int i = 0; i < shidaolevel.length; i++) {
                GameMap gameMap = GameLine.getGameMapname(1, shidaolevel[i]);
                List<GameObjectChar> gameSessions = insertionSort(gameMap.sessionList);
                String mingname = "";
                int mingci = 0;
                for (int j = 0; j < gameSessions.size(); j++) {
                    if (mingname.equals(gameSessions.get(j).gameTeam.duiwu.get(0).name + gameSessions.get(j).gameTeam.duiwu.get(0).shidaodaguaijifen)) {
                        continue;
                    }
                    mingname = gameSessions.get(j).gameTeam.duiwu.get(0).name + gameSessions.get(j).gameTeam.duiwu.get(0).shidaodaguaijifen;
                    mingci++;
                }
                if (mingci == 1) {
                    for (int j = 0; j < gameSessions.size(); j++) {
                        for (int k = 0; k < gameSessions.get(j).gameTeam.duiwu.size(); k++) {
                            gameSessions.get(j).gameTeam.duiwu.get(k).chenghao.put("试道王者", "试道王者");
                            GameUtil.chenghaoxiaoxi(gameSessions.get(j).gameTeam.duiwu.get(k));
                            Vo_20481_0 vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了#R试道王者#n的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            gameSessions.get(j).gameTeam.duiwu.get(k).extra_life = gameSessions.get(j).gameTeam.duiwu.get(k).extra_life + 200000;
                            vo_20481_9 = new Vo_20481_0();
                            vo_20481_9.msg = "你获得了200000元宝的称谓。";
                            vo_20481_9.time = (int) (System.currentTimeMillis() / 1000);
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M20481_0(), vo_20481_9);
                            ListVo_65527_0 listVo_65527_0 = GameUtil.a65527(gameSessions.get(j).gameTeam.duiwu.get(k));
                            GameObjectCharMng.getGameObjectChar(gameSessions.get(j).gameTeam.duiwu.get(k).id).sendOne(new M65527_0(), listVo_65527_0);
                            GameUtilRenWu.shidaohuicheng(gameSessions.get(j).gameTeam.duiwu.get(k));
                        }
                        break;
                    }


                }
            }
        }
    }

    @Scheduled(fixedRate = 10000) 
    public void autofightgonggao() {
        long time = System.currentTimeMillis();
        List<Notice> all = GameData.that.baseNoticeService.findAll();
        for (int i = 0; i < all.size(); i++) {
            if (gonggaotim + all.get(i).getTime() * 60000 < time) {
                Vo_16383_0 vo_16383_0 = new Vo_16383_0();
                vo_16383_0.channel = 19;
                vo_16383_0.id = 0;
                vo_16383_0.name = "";
                vo_16383_0.msg = all.get(i).getMessage();
                long times = System.currentTimeMillis() / 1000;
                vo_16383_0.time = (int) times;
                vo_16383_0.privilege = 0;
                vo_16383_0.server_name = "涅槃重生22";
                vo_16383_0.show_extra = 2;
                vo_16383_0.compress = 0;
                vo_16383_0.orgLength = 65535;
                vo_16383_0.cardCount = 0;
                vo_16383_0.voiceTime = 0;
                vo_16383_0.token = "";
                vo_16383_0.checksum = 0;
                vo_16383_0.iid_str = "";
                vo_16383_0.has_break_lv_limit = 0;
                vo_16383_0.skill = 1;
                vo_16383_0.type = 1;

                GameObjectCharMng.sendAll(new M16383_0(), vo_16383_0);
                gonggaotim = System.currentTimeMillis();
            }
        }
    }


    public static List<GameObjectChar> insertionSort(List<GameObjectChar> sessionList) {
        for (int i = 0; i < sessionList.size() - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (sessionList.get(j - 1).chara.shidaodaguaijifen >= sessionList.get(j).chara.shidaodaguaijifen)
                    break;
                GameObjectChar temp = sessionList.get(j);
                
                sessionList.set(j, sessionList.get(j - 1));
                sessionList.set(j - 1, temp);
            }
        }
        return sessionList;
    }

    @Scheduled(fixedRate = 2000) 
    public void autofightromve() {
        List<GameObjectChar> sessionList = GameObjectCharMng.getGameObjectCharList();
        long time = System.currentTimeMillis();
        for (int i = 0; i < sessionList.size(); i++) {
            try {
                if (sessionList.get(i).gameMap.id == 38004 && sessionList.get(i).gameTeam == null) {
                    GameUtilRenWu.shidaohuicheng(sessionList.get(i).chara);
                }
                if (sessionList.get(i).heartEcho != 0 && sessionList.get(i).heartEcho + 180000 < time) {
                    sessionList.get(i).offline();
                    sessionList.remove(sessionList.get(i));
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }


    }

}