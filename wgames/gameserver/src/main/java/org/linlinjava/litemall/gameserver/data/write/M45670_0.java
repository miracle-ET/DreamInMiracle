package org.linlinjava.litemall.gameserver.data.write;

import io.netty.buffer.ByteBuf;
import org.linlinjava.litemall.gameserver.data.GameWriteTool;
import org.linlinjava.litemall.gameserver.data.vo.Vo_64989_0;
import org.linlinjava.litemall.gameserver.netty.BaseWrite;
import org.springframework.stereotype.Service;

@Service
public class M45670_0 extends BaseWrite {
    @Override
    protected void writeO(ByteBuf writeBuf, Object object) {

    }

    @Override
    public int cmd() {
        return 45670;
    }
}







