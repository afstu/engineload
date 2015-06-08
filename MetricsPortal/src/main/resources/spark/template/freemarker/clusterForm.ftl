<div class="starter-template">
    <form class="form-horizontal" role="form" id='cluster-create-form' method='POST' action='/clusters/create'>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="title">Title: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="naam" name='cluster-naam' placeholder="Voer de naam van deze cluster in" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="status">Status: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="status" name='cluster-status' placeholder="DTAP" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="director">Director: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="director" name='cluster-director' placeholder="Voer de primary director van deze cluster in" />
            </div>
        </div>
    </form>
 
    <label for="content">Content</label>
    <textarea class="form-control" name='cluster-beschrijving' id="beschrijving" rows='3' cols='30' form='cluster-create-form' placeholder="Voer een beschrijving van dit cluster in..."></textarea>
    <input type='submit' value='Publish' class="btn btn-primary" form='cluster-create-form' />
</div>