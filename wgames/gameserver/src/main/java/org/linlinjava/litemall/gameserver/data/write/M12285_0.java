package org.linlinjava.litemall.gameserver.data.write;

import io.netty.buffer.ByteBuf;
import org.linlinjava.litemall.db.domain.NpcPoint;
import org.linlinjava.litemall.gameserver.data.GameWriteTool;
import org.linlinjava.litemall.gameserver.netty.BaseWrite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class M12285_0 extends BaseWrite {
    @Override
    protected void writeO(ByteBuf writeBuf, Object object) {
        int id = (int) object;
        GameWriteTool.writeInt(writeBuf,id);
        GameWriteTool.writeByte(writeBuf,32768);
    }

    @Override
    public int cmd() {
        return 12285;
    }
}











