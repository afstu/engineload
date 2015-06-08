<div class="starter-template">
    <form class="form-horizontal" role="form" id='director-create-form' method='POST' action='/director/create'>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="directorNaam">Director Naam: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="directorNaam" name='director-Naam' placeholder="Voer Director naam in" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="directorPoort">Poort: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="directorPoort" name='director-poort' placeholder="Voer Director TCP poort in" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="driverNaam">Driver Naam: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="driverNaam" name='director-driverNaam' placeholder="Voer Drivernaam in" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="driverWachtwoordt">Wachtwoord: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="driverWachtwoord" name='director-driverWachtwoord' placeholder="Voer Driverwachtwoord in" />
            </div>
        </div>
        
    </form>
 
    <label for="director-beschrijving">Beschrijving</label>
    <textarea class="form-control" name='director-beschrijving' id="beschrijving" rows='3' cols='30' form='director-create-form' placeholder="Voer Director beschrijving in"></textarea>
    <input type='submit' value='Publish' class="btn btn-primary" form='director-create-form' />
</div>