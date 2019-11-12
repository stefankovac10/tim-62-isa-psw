Vue.component("login-form", {
    data: function() {
        return {
            email: undefined,
            password: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
        <h1 class="p-2">Log in</h1>
        
        <label class="p-2">E-mail</label>
        <input type="text" class="p-2" id="email" v-model="email">
        <label class="p-2" for="password">Password</label>
        <input type="password" class="p-2" id="password" v-model="password" placeholder="Password">
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>	  
</div>
`
});

Vue.component("registration-form", {
    data: function () {
        return {
            password: undefined,
            name: undefined,
            surname: undefined,
            jmbg: undefined,
            telephone: undefined,
            country: undefined,
            city: undefined,
            address: undefined,
            email: undefined
        }
    },
    template: ` 
<div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
        <h1 class="p-2">Registration</h1><br>
          
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
        <button class="btn btn-primary p-2" v-on:click="register" >Submit</button>
    </form>
</div>
      
`,
});

const Register = { template: '<registration-form></registration-form>' }
const Login = { template: '<login-form></login-form>' }


const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/register', component: Register },
        { path: '/login', component: Login }
    ]
});

var app = new Vue({
    router,
    el: '#ono'
});
