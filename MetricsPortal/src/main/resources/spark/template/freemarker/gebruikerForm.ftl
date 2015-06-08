<div class="starter-template">
    <form class="form-horizontal" role="form" id='gebruiker-create-form' method='POST' action='/gebruikers/create'>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="gebruiker">Gebruiker: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="corpkey" name='gebruiker-corpkey' placeholder="Voer Corporate Key gebruiker in" />
            </div>
       </div>
       <div class="form-group">
	       	<label class="col-sm-3 control-label" for="gebruiker">Voornaam: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="voornaam" name='gebruiker-voornaam' placeholder="Voer voornaam gebruiker in" />
            </div>
       </div>
       <div class="form-group">
	       <label class="col-sm-3 control-label" for="gebruiker">Achternaam: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="achternaam" name='gebruiker-achternaam' placeholder="Voer achternaam gebruiker in" />
            </div>
       </div>
       <div class="form-group">
       		<label class="col-sm-3 control-label" for="gebruiker">Wachtwoord: </label>
            <div class="col-sm-5">
                <input class="form-control" type='password' id="wachtwoord" name='gebruiker-wachtwoord' placeholder="Voer wachtwoord in" />
            </div>
       </div>
    </form>
 <div class="form-group">
    <label for="beschrijving">Gebruiker Beschrijving</label>
    <textarea class="form-control" name='gebruiker-beschrijving' id="gebruiker-beschrijving" rows='3' cols='30' form='gebruiker-create-form' placeholder="Voer Beschrijving in"></textarea>
    <input type='submit' value='Publish' class="btn btn-primary" form='gebruiker-create-form' />
</div>
</div>