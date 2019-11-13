Vue.component("clinic", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Clinic</h1>	  
</div>
`
});

Vue.component("business-report", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Business report</h1>	  
</div>
`
});

Vue.component("add-appointment", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Add appointment</h1>	  
</div>
`
});

Vue.component("add-room", {
   data: function () {
       return {}
   } ,
    template: `
<div class="d-flex p-2 justify-content-center">
    <h1>Add room</h1>	  
</div>
    `
});

Vue.component("manage-rooms", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Manage rooms</h1>	  
</div>
`
});

Vue.component("add-type", {
    data: function () {
        return {}
    } ,
    template: `
<div class="d-flex p-2 justify-content-center">
    <h1>Add type</h1>	  
</div>
    `
});

Vue.component("manage-types", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Manage types</h1>	  
</div>
`
});

Vue.component("add-doctor", {
    data: function() {
        return {
            password: undefined,
            name: undefined,
            surname: undefined,
            jmbg: undefined,
            telephone: undefined,
            country: undefined,
            city: undefined,
            address: undefined,
            email: undefined,
            clinic : undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
        <h1 class="p-2">Add doctor</h1><br>
          
        <label class="p-2">Name</label>
        <input type="text" class="p-2" id="name" name="name" v-model="name">

        <label class="p-2">Surname</label>
        <input type="text" class="p-2" id="surname" name="surname" v-model="surname">
        
        <label class="p-2">JMBG</label>
        <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg">
        
        <label class="p-2">Telephone</label>
        <input type="text" class="p-2" id="telephone" name="telephone" v-model="telephone">

        <label class="p-2">Country</label>
        <input type="text" class="p-2" id="country" name="country" v-model="country">

        <label class="p-2">City</label>
        <input type="text" class="p-2" id="city" name="city" v-model="city">

        <label class="p-2">Address</label>
        <input type="text" class="p-2" id="address" name="address" v-model="address">

        <label for="staticEmail" class="p-2">E-mail</label>
        <input type="email" class="p-2" id="email" name="email" v-model="email" aria-describedby="emailHelp" placeholder="Enter email"><br>
        
        <label class="p-2" for="password">Password</label>
        <input type="password" class="p-2" id="password" name="password" v-model="password" placeholder="Password">
        
        <br>
        <label class="p-2">Clinic : [current clinic]</label>
        <br>
        
        <button class="btn btn-primary p-2" v-on:click="register" >Submit</button>
    </form>  
</div>
`
});

Vue.component("doctors", {
    data: function() {
        return {

        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <h1>Doctors</h1>	  
</div>
`
});

Vue.component("profile", {
    data: function() {
        return {
            name: undefined,
            surname: undefined,
            jmbg: undefined,
            telephone: undefined,
            country: undefined,
            city: undefined,
            address: undefined,
            email: undefined,
            clinic : undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2">
        <h1>Clinic administrator</h1>
        <p>Name: {{name}} {{surname}}</p>
        <p>JMBG: {{jmbg}}</p>
        <p>Telephone: {{telephone}}</p>
        <p>Address: {{address}}, {{city}}, {{country}}</p>
        <p>Email: {{email}}</p>
        <p>Assigned clinic: {{clinic}}</p>  
    </div>
</div>
`
});

const Clinic = { template: '<clinic></clinic>' };
const BusinessReport = { template: '<business-report></business-report>' };
const AddAppointment = { template : '<add-appointment></add-appointment>'};
const AddRoom = { template : '<add-room></add-room>'};
const ManageRooms = { template : '<manage-rooms></manage-rooms>' };
const AddType = { template: '<add-type></add-type>' };
const ManageTypes = { template : '<manage-types></manage-types>'};
const AddDoctor = { template : '<add-doctor></add-doctor>'};
const Doctors = { template : '<doctors></doctors>'};
const Profile = { template : '<profile></profile>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/clinic', component: Clinic },
        { path: '/businessReport', component: BusinessReport },
        { path: '/addAppointment', component: AddAppointment },
        { path: '/addRoom', component: AddRoom },
        { path: '/rooms', component: ManageRooms },
        { path: '/addType', component: AddType },
        { path: '/types', component: ManageTypes },
        { path: '/addDoctor', component: AddDoctor },
        { path: '/doctors', component: Doctors },
        { path: '/profile', component: Profile }
    ]
});

var app = new Vue({
    router,
    el: '#cadminOno'
});