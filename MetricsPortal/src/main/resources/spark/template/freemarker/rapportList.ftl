<#if hasNoRapporten??>
    <div class="starter-template">
        <h1>${hasNoRapporten}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list Rapporten as rapport>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.U)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.S)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.E)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.C)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>    
        </#list>
    </div>
</#if>