<#if hasNoRollen??>
    <div class="starter-template">
        <h1>${hasNoRollen}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list Rollen as rol>
            <h3>${rol.getRolNaam()}</h3>
            <h4>${rol.getRolBeschrijving()}</h4>
            <h4>${rol.getEditLink()} | ${rol.getDeleteLink()}</h4>
        </#list>
    </div>
</#if>