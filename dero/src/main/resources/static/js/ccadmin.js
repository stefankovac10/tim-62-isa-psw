Vue.component("medicaments", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    Medicaments
</div>
`
});

Vue.component("requests", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
   Requests 
</div>
`
});

Vue.component("clinics", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    Clinics
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

const Medicaments = { template: '<medicaments></medicaments>' }
const Requests = { template: '<requests></requests>' }
const Clinics = { template: '<clinics></clinics>' }
const Admins = { template: '<admins></admins>' }
const Diagnosis = { template: '<diagnosis></diagnosis>' }



const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: '/medicaments', component: Medicaments },
        { path: '/admins', component: Admins },
        { path: '/requests', component: Requests },
        { path: '/clinics', component: Clinics },
        { path: '/diagnosis', component: Diagnosis }
    ]
});

var app = new Vue({
    router,
    el: '#ccAdminHomepage'
});