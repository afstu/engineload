<div class="starter-template">
    <form class="form-horizontal" role="form" id='graphite-create-form' method='POST' action='/graphite/create'>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="graphiteUrl">Graphite URL: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="graphiteUrl" name='graphite-url' placeholder="Voer Graphite URL in" />
            </div>
        </div>
    </form>
       <input type='submit' value='Publish' class="btn btn-primary" form='graphite-create-form' />
</div>