<template>
  <div class="d-flex p-2 justify-content-center">
    <form>
      <fieldset>
        <legend>Add clinic</legend>
        <div class="form-group">
          <label for="exampleInputEmail1">Name</label>
          <input
            type="text"
            class="form-control"
            id="nameClinic"
            v-model="name"
            placeholder="Enter name"
          />
        </div>
        <div class="form-group">
          <label for="exampleInputEmail1">Address</label>
          <input
            type="text"
            class="form-control"
            id="addressClinic"
            v-model="address"
            placeholder="Enter address"
          />
        </div>
        <div class="form-group">
          <label for="exampleTextarea">Description</label>
          <textarea
            class="form-control"
            id="descriptionClinic"
            v-model="description"
            rows="3"
          ></textarea>
        </div>
        <button type="submit" class="btn btn-primary" v-on:click="add">Add</button>
      </fieldset>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios,axios)
export default {
  name: "addClinic",
  data: function() {
    return {
      name: undefined,
      address: undefined,
      description: undefined
    };
  },
  methods : {
    add : function(){
      if(this.name === undefined || this.name === '' || this.address ===  undefined || this.address === '' || this.description === undefined || this.description === ''){
        alert("All field must be filled");
        return;
      }
      
      Vue.axios.get('localhost:8080/api/clinics/1').then((response =>{
        alert("hello");
        alert(response.data)
        
      }))


    }
  }
};
</script>
