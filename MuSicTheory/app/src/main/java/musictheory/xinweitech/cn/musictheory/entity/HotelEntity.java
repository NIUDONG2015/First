package musictheory.xinweitech.cn.musictheory.entity;

import java.util.ArrayList;

/**
 *
 */

public class HotelEntity {

    public ArrayList<TagsEntity> allTagsList;//所有的数据

    public class TagsEntity {   //
        public String tagsName;
        private ArrayList<TagInfo> tagInfoList;//头下面对应的详情页

        public ArrayList<TagInfo> getTagInfoList() {
            return tagInfoList;
        }

        public void setTagInfoList(ArrayList<TagInfo> tagInfoList) {
            this.tagInfoList = tagInfoList;
        }

        public class TagInfo {
            public String tagName;

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }
}
