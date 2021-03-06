package com.woorea.openstack.quantum.model;

/*
 * Modifications copyright (c) 2017 AT&T Intellectual Property
 */

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Network Extension supporting both Provider networks and Multi-Provider networks.
 * The attributes for both of these network extensions are included.
 * It is the responsibility of users to populate only one or the other.  It is
 * also critical that the Mapper is set for serialization inclusion.NON_NULL, or
 * both would be sent to Openstack which would cause an error.
 * 
 */
@SuppressWarnings("serial")
@JsonRootName("network")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Network implements Serializable {

    private String status;

    private List<String> subnets;

    private String name;

    @JsonProperty("admin_state_up")
    private Boolean adminStateUp;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("provider:physical_network")
    private String providerPhysicalNetwork;

    @JsonProperty("provider:network_type")
    private String providerNetworkType;

    @JsonProperty("provider:segmentation_id")
    private Integer providerSegmentationId;

    @JsonProperty("router:external")
    private String routerExternal;

    private String id;

    private String shared;
    
    private List<Segment> segments;

    /**
     * @return the status
     */
    @JsonIgnore
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the subnets
     */
    @JsonIgnore
    public List<String> getSubnets() {
        return subnets;
    }

    /**
     * @param subnets
     *            the subnets to set
     */
    @JsonProperty
    public void setSubnets(List<String> subnets) {
        this.subnets = subnets;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the providerPhyNet
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getProviderPhyNet() {
        return getProviderPhysicalNetwork();
    }

    /**
     * @param providerPhyNet
     *            the providerPhyNet to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setProviderPhyNet(String providerPhyNet) {
        setProviderPhysicalNetwork(providerPhyNet);
    }

    /**
     * @return the adminStateUp
     */
    @JsonIgnore
    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    public Boolean getAdminStateUp() {
        return adminStateUp;
    }

    /**
     * @param adminStateUp
     *            the adminStateUp to set
     */
    public void setAdminStateUp(Boolean adminStateUp) {
        this.adminStateUp = adminStateUp;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     *            the tenantId to set
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return the netType
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getNetType() {
        return getProviderNetworkType();
    }

    /**
     * @param netType
     *            the netType to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setNetType(String netType) {
        setProviderNetworkType(netType);
    }

    /**
     * @return the routerExternal
     */
    public String getRouterExternal() {
        return routerExternal;
    }

    /**
     * @param routerExternal
     *            the routerExternal to set
     */
    public void setRouterExternal(String routerExternal) {
        this.routerExternal = routerExternal;
    }

    /**
     * @return the id
     */
    @JsonIgnore
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the shared
     */
    public String getShared() {
        return shared;
    }

    /**
     * @param shared
     *            the shared to set
     */
    public void setShared(String shared) {
        this.shared = shared;
    }

    /**
     * @return the providerSegID
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getProviderSegID() {
        return getProviderSegmentationId() == null ? null : Integer.toString(getProviderSegmentationId());
    }

    /**
     * @param providerSegID
     *            the providerSegID to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setProviderSegID(String providerSegID) {
        setProviderSegmentationId(providerSegID == null ? null : Integer.parseInt(providerSegID));
    }

    public String getProviderNetworkType() {
        return providerNetworkType;
    }

    public void setProviderNetworkType(String providerNetworkType) {
        this.providerNetworkType = providerNetworkType;
    }

    public String getProviderPhysicalNetwork() {
        return providerPhysicalNetwork;
    }

    public void setProviderPhysicalNetwork(String providerPhysicalNetwork) {
        this.providerPhysicalNetwork = providerPhysicalNetwork;
    }

    public Integer getProviderSegmentationId() {
        return providerSegmentationId;
    }

    public void setProviderSegmentationId(Integer providerSegmentationId) {
        this.providerSegmentationId = providerSegmentationId;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Function to detect and return the network type
     */
    public enum NetworkType { BASIC, PROVIDER, MULTI_PROVIDER };
    
    @JsonIgnore
    public NetworkType getNetworkType () {
    	if (segments != null)
    		return NetworkType.MULTI_PROVIDER;
    	else if (providerNetworkType != null)
    		return NetworkType.PROVIDER;
    	else
    		return NetworkType.BASIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	StringBuffer buf = new StringBuffer("Network [id=" + id + ", name=" + name + ", subnets="
                + subnets + ", status=" + status + ", admin_state_up=" + adminStateUp + ", tenant_id=" +
                tenantId + ", shared=" + shared + ", router:external=" + routerExternal);
    	if (getNetworkType() == NetworkType.PROVIDER)
    		buf.append (", provider:physical_network=" + providerPhysicalNetwork +
                ", provider:network_type=" + providerNetworkType  +
                ", provider:segmentation_id=" + providerSegmentationId);
    	if (getNetworkType() == NetworkType.MULTI_PROVIDER) {
    		buf.append (", segments: ");
    		for (Segment s : segments)
    			buf.append (s.toString()).append(" ");
    	}
    	buf.append ("]");
    	return buf.toString();
    }
}
