<#if hasNoRollen??>
    <div class="starter-template">
        <h1>${hasNoRollen}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list Gebruikers as gebruiker>
            <h3>${gebruiker.getCorpKey()}</h3>
            <h4>${gebruiker.getVoorNaam()} ${gebruiker.getAchterNaam()}</h4>
            <h4>${gebruiker.getGebruikerBeschrijving()}</h4>
            <h4>${gebruiker.getEditLink()} | ${gebruiker.getDeleteLink()}</h4>
        </#list>
    </div>
</#if>