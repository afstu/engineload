<div class="starter-template">
    <form class="form-horizontal" role="form" id='rol-create-form' method='POST' action='/rollen/create'>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="rolNaam">Rol Naam: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="naam" name='rol-naam' placeholder="Voer Rol Naam in" />
            </div>
        </div>
    </form>
 
    <label for="beschrijving">Rol Beschrijving</label>
    <textarea class="form-control" name='rol-beschrijving' id="beschrijving" rows='3' cols='30' form='rol-create-form' placeholder="Voer Rol Beschrijving in"></textarea>
    <input type='submit' value='Publish' class="btn btn-primary" form='rol-create-form' />
</div>