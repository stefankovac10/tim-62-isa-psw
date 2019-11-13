Vue.component("medicament", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    Medicament
</div>
`
});

Vue.component("requests", {
    data: function() {
        return {
            mode: 'VIEW'
        }
    },
    template: ` 
    <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
       <div class="card mb-3" style="min-width: 250px;">
          <h3 class="card-header">Username</h3>
          <div class="card-body">
            <h5 class="card-title">Name</h5>
            <h6 class="card-subtitle text-muted">JMBG</h6>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item">email</li>
            <li class="list-group-item">address</li>
            <li class="list-group-item">city</li>
            <li class="list-group-item">country</li>
            <li class="list-group-item">phone</li>
          </ul>
          <div class="card-body">
                <button type="button" class="btn btn-success" v-on:click="accept">Accept</button>
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Decline</button>
          </div>
        </div>
        <div class="modal fade" ref="modal" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form>
                  <div class="form-group">
                    <textarea class="form-control" id="message-text"></textarea>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" v-on:click="decline">Send</button>
              </div>
            </div>
          </div>
        </div>
    </div>
    `,
    methods: {
        accept: function () {
            
        },
        decline: function () {

        }
    }
});

Vue.component("clinics", {
    data: function() {
        return {
            name: undefined,
            address: undefined,
            description: undefined,
            mode: 'VIEW'
        }
    },
    template: ` 
    <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <div v-if="mode == 'VIEW'">      
           <div class="card border-primary mb-3" style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px">
              <div class="card-header">Address</div>
              <div class="card-body">
                <h4 class="card-title">Name</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <button type="button" class="btn btn-primary" v-on:click="edit">Edit</button>
                <button type="button" class="btn btn-danger" v-on:click="remove">Delete</button>
              </div>
            </div> 
        </div>
        <div v-else>
            <form>
                  <fieldset>
                    <legend>Add clinic</legend>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Name</label>
                          <input type="text" class="form-control" id="nameClinic" v-model="name" placeholder="Enter name">
                        </div>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Address</label>
                          <input type="text" class="form-control" id="addressClinic" v-model="address" placeholder="Enter address">
                        </div>
                        <div class="form-group">
                          <label for="exampleTextarea">Description</label>
                          <textarea class="form-control" id="descriptionClinic" v-model="description" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary" v-on:click="save">Save</button>
                  </fieldset>
            </form>
        </div>
    </div>
        `,
    methods: {
        edit: function () {
            this.mode='EDIT'
        },
        save: function () {
            this.mode='VIEW'
        },
        remove: function () {

        }
    }
});

Vue.component("addClinic", {
    data: function() {
        return {
            name: undefined,
            address: undefined,
            description: undefined
        }
    },
    template: ` 
    <div class="d-flex p-2 justify-content-center">
            <form>
                  <fieldset>
                    <legend>Add clinic</legend>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Name</label>
                          <input type="text" class="form-control" id="nameClinic" v-model="name" placeholder="Enter name">
                        </div>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Address</label>
                          <input type="text" class="form-control" id="addressClinic" v-model="address" placeholder="Enter address">
                        </div>
                        <div class="form-group">
                          <label for="exampleTextarea">Description</label>
                          <textarea class="form-control" id="descriptionClinic" v-model="description" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Add</button>
                  </fieldset>
            </form>
    </div>
        `
});

Vue.component("admins", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    Admins  
</div>
`
});

Vue.component("diagnosis", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    Diagnosis
</div>
`
});

const Medicament = { template: '<medicament></medicament>' }
const Requests = { template: '<requests></requests>' }
const AddClinic = { template: '<addClinic></addClinic>' }
const Clinics = { template: '<clinics></clinics>' }
const Admins = { template: '<admins></admins>' }
const Diagnosis = { template: '<diagnosis></diagnosis>' }



const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/', component: Requests },
        { path: '/medicament', component: Medicament },
        { path: '/admins', component: Admins },
        { path: '/requests', component: Requests },
        { path: '/clinics', component: Clinics },
        { path: '/diagnosis', component: Diagnosis },
        { path: '/addClinic', component: AddClinic }
    ]
});

var app = new Vue({
    router,
    el: '#ccAdminHomepage'
});