package com.gupao.vip.drools.edu.entity;

public class ParamInfo {

    private String murex3Id ;
    private String localId ;
    private String isoCode ;
    private String settleType ;
    private String dataStatus ;

    public ParamInfo() {
    }

    public ParamInfo(String murex3Id, String localId, String isoCode, String settleType, String dataStatus) {
        this.murex3Id = murex3Id;
        this.localId = localId;
        this.isoCode = isoCode;
        this.settleType = settleType;
        this.dataStatus = dataStatus;
    }

    public String getMurex3Id() {
        return murex3Id;
    }

    public ParamInfo setMurex3Id(String murex3Id) {
        this.murex3Id = murex3Id;
        return this;
    }

    public String getLocalId() {
        return localId;
    }

    public ParamInfo setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public ParamInfo setIsoCode(String isoCode) {
        this.isoCode = isoCode;
        return this;
    }

    public String getSettleType() {
        return settleType;
    }

    public ParamInfo setSettleType(String settleType) {
        this.settleType = settleType;
        return this;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public ParamInfo setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    @Override
    public String toString() {
        return "ParamInfo{" +
                "murex3Id='" + murex3Id + '\'' +
                ", localId='" + localId + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", settleType='" + settleType + '\'' +
                ", dataStatus='" + dataStatus + '\'' +
                '}';
    }
}
