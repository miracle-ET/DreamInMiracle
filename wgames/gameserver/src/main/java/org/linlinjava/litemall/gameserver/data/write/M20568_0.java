package org.linlinjava.litemall.gameserver.data.write;

import org.linlinjava.litemall.gameserver.data.GameWriteTool;
import org.linlinjava.litemall.gameserver.netty.BaseWrite;
import org.springframework.stereotype.Service;
import io.netty.buffer.ByteBuf;
import org.linlinjava.litemall.gameserver.data.vo.Vo_20568_0;

@Service
public class M20568_0 extends BaseWrite {
    @Override
    protected void writeO(ByteBuf writeBuf, Object object) {
        Vo_20568_0 object1 = (Vo_20568_0) object;
        GameWriteTool.writeString(writeBuf, object1.gid);
    }

    @Override
    public int cmd() {
        return 20568;
    }
}


