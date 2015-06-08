<#if hasNoDirectors??>
    <div class="starter-template">
        <h1>${hasNoDirectors}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list directors as director>
            <h3>${director.getDirectorNaam()}</h3>
            <h4>${director.getClusterNaam()}</h4>
            <h4>${director.getEditLink()} | ${article.getDeleteLink()}</h4>
        </#list>
    </div>
</#if>