<#if hasNoGraphite??>
    <div class="starter-template">
        <h1>${hasNoGraphite}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list Graphites as graphite>
            <h3>${graphite.getGraphiteUrl()}</h3>
            <h4>${graphite.getEditLink()} | ${graphite.getDeleteLink()}</h4>
        </#list>
    </div>
</#if>