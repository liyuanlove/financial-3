package com.imooc.seller.enums;

/**
 * 渠道配置信息
 * Created by songyouyu on 2018/6/17
 */
public enum ChanEnum {

    ABC("111", "ABC", "F:\\IdeaWorkspace\\Log\\financial");

    private String chanId;

    private String chanName;

    private String ftpPath, ftpUser, ftpPwd;

    private String rootDir;

    ChanEnum(String chanId, String chanName, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.rootDir = rootDir;
    }

    public String getChanId() {
        return chanId;
    }

    public String getChanName() {
        return chanName;
    }

    public String getFtpPath() {
        return ftpPath;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public String getFtpPwd() {
        return ftpPwd;
    }

    public String getRootDir() {
        return rootDir;
    }

    /**
     * 根据渠道编号获取渠道配置
     * @param chanId
     * @return
     */
    public static ChanEnum getByChanId(String chanId) {
        for (ChanEnum chanEnum : ChanEnum.values()) {
            if (chanEnum.getChanId().equals(chanId)) {
                return chanEnum;
            }
        }
        return null;
    }

}
