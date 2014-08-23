package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.ids.FieldIdItem;
import com.github.zxh.classpy.dexfile.ids.ProtoIdItem;
import com.github.zxh.classpy.dexfile.ids.StringIdItem;
import com.github.zxh.classpy.dexfile.ids.TypeIdItem;
import java.util.Arrays;
import java.util.List;

/**
 *
 * http://source.android.com/devices/tech/dalvik/dex-format.html
 * @author zxh
 */
public class DexFile extends DexComponent {
    
    private HeaderItem header;
    private DcList<StringIdItem> stringIds;
    private DcList<TypeIdItem> typeIds;
    private DcList<ProtoIdItem> protoIds;
    private DcList<FieldIdItem> fieldIds;

    @Override
    protected void readContent(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
        stringIds = reader.readList(header.getStringIdsSize(), StringIdItem::new);
        typeIds = reader.readList(header.getTypeIdsSize(), TypeIdItem::new);
        protoIds = reader.readList(header.getProtoIdsSize(), ProtoIdItem::new);
        fieldIds = reader.readList(header.getFieldIdsSize(), FieldIdItem::new);
        // todo
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header, stringIds, typeIds, protoIds, fieldIds);
    }
    
}