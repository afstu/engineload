<#if hasNoClusters??>
    <div class="starter-template">
        <h1>${hasNoClusters}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list Clusters as cluster>
            <h3>${cluster.getClusterNaam()}</h3>
            <h4>${cluster.getClusterStatus()}</h4>
            <h4>${cluster.getClusterDirector()}</h4>
            <h4>${cluster.getClusterBeschrijving()}</h4>
            <h4>${cluster.getEditLink()} | ${cluster.getDeleteLink()}</h4>
        </#list>
    </div>
</#if>