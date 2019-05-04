package com.example.common.spider.ygo;

public class Card {

    public static String ID = "id";
    public static String HASH_ID = "hash_id";
    public static String PASSWORD = "password";
    public static String NAME = "name";
    public static String NAME_JA = "name_ja";
    public static String NAME_EN = "name_en";
    public static String LOCALE = "locale";
    public static String TYPE_ST = "type_st";
    public static String TYPE_VAL = "type_val";
    public static String IMG_URL = "img_url";
    public static String LEVEL = "level"; // 星级
    public static String ATTRIBUTE = "attribute"; // 属性
    public static String RACE = "race"; // 种族
    public static String ATK = "atk"; // 攻击
    public static String DEF = "def"; // 防御
    public static String PEND_L = "pend_l"; // 左刻度
    public static String PEND_R = "pend_r"; // 右刻度
    public static String LINK = "link";
    public static String LINK_ARROW = "link_arrow";
    public static String NAME_NW = "name_nw";
    public static String DESC = "desc";
    public static String DESC_NW = "desc_nw";
    public static String RARE = "rare";
    public static String PACKAGES = "packages";
    public static String HREF = "href";

    private long id;
    private String hash_id;
    private String password;
    private String name;
    private String name_ja;
    private String name_en;
    private int locale;
    private String type_st;
    private String type_val;
    private String img_url;
    private int level; // 星级
    private String attribute; // 属性
    private String race; // 种族
    private int atk; // 攻击
    private int def; // 防御
    private int pend_l; // 左刻度
    private int pend_r; // 右刻度
    private int link;
    private int link_arrow;
    private String name_nw;
    private String desc;
    private String desc_nw;
    private String rare;
    private String packages;
    private String href;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHasf_id() {
        return hash_id;
    }

    public void setHash_id(String hash_id) {
        this.hash_id = hash_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ja() {
        return name_ja;
    }

    public void setName_ja(String name_ja) {
        this.name_ja = name_ja;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public int getLocale() {
        return locale;
    }

    public void setLocale(int locale) {
        this.locale = locale;
    }

    public String getType_st() {
        return type_st;
    }

    public void setType_st(String type_st) {
        this.type_st = type_st;
    }

    public String getType_val() {
        return type_val;
    }

    public void setType_val(String type_val) {
        this.type_val = type_val;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getPend_l() {
        return pend_l;
    }

    public void setPend_l(int pend_l) {
        this.pend_l = pend_l;
    }

    public int getPend_r() {
        return pend_r;
    }

    public void setPend_r(int pend_r) {
        this.pend_r = pend_r;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink_arrow() {
        return link_arrow;
    }

    public void setLink_arrow(int link_arrow) {
        this.link_arrow = link_arrow;
    }

    public String getName_nw() {
        return name_nw;
    }

    public void setName_nw(String name_nw) {
        this.name_nw = name_nw;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc_nw() {
        return desc_nw;
    }

    public void setDesc_nw(String desc_nw) {
        this.desc_nw = desc_nw;
    }

    public String getRare() {
        return rare;
    }

    public void setRare(String rare) {
        this.rare = rare;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
