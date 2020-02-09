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
        <button type="submit" class="btn btn-primary" v-on:click.prevent="add">Add</button>
      </fieldset>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
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
    add: function(){
      if(this.name === undefined || this.name === '' || this.address ===  undefined || this.address === '' || this.description === undefined || this.description === ''){
        this.$vToastify.info({
              body: "Please, fill all the information",
              title: "Info",
              type: "info",
              canTimeout: true,
              append: false, duration: 2000
        });
        return;
      }
    
      httpClient
        .post("/clinics", {
          name: this.name,
          address: this.address,
          description: this.description
          })
          .then(response => {
            this.response = response;  
            this.$vToastify.success({
              body: "Clinic "+ this.name + " has been added",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
             });
          })
          .catch(error => {
            this.error = error;
            this.$vToastify.error({
              body: "Clinic "+ this.name + " already exists",
              title: "Error",
              type: "error",
              canTimeout: true,
              append: false, duration: 2000
            });
          });
          
        this.$router.push("/ccadmin/clinics");
        
    }
  }
};
</script>
