<template>
    <div>
            <h1 align="center">Avaliable doctors</h1>
            <br>
            <div>
              <multiselect
                  v-model="doctorsSelected"
                  tag-placeholder="Add this as new tag"
                  placeholder="Search or add a tag"
                  label="firstName"
                  track-by="id"
                  :options="doctors"
                  :multiple="true"
                  :custom-label="customLabel"
                  :taggable="true"
              ></multiselect>
            </div>
              <br>
              <div v-for="d in doctorsSelected" v-bind:key="d.id">
                  Dr. {{d.firstName}} {{d.lastName}} - {{d.telephone}} 
                  <br>  
                  <br>
              </div>
              <br>
              <h5 align="center">Please select doctors for operation</h5>
             <button class="btn btn-success" style="position: absolute; right: 0; bottom:0; margin: 35px" v-on:click="reserve()" >Assign</button>

    </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import Multiselect from "vue-multiselect";
//import _ from "lodash";
export default {
components: {
    Multiselect
},
props:{
    room: Object,
    id: undefined
},
data: function(){
    return{
        doctorsSelected: undefined,
        doctors: [],
        roomRequest: {
          
        }
    }
},
mounted(){
    
    httpClient
      .get("/users/doc/avaliable/"+ this.id+"/"+this.room.nextAvailable)
      .then(response => {
        this.doctors = response.data;
      })
      .catch(error => {
        alert(error);
      });
},
methods:{
  customLabel({firstName, lastName}){
    return  `${firstName}  ${lastName}`
  },
  reserve: function() {
      this.roomRequest.requestId = this.id;
      this.roomRequest.room = this.room;
      this.roomRequest.doctors = this.doctorsSelected;
      if(this.doctorsSelected === undefined || this.doctorsSelected === null){
        this.$vToastify.error({
            body: "Please, choose doctors",
            title: "Warning",
            type: "warning",
            canTimeout: true,
            append: false,
            duration: 2000
          });
          return;
      }
      
      httpClient
        .post("/cadmin/reserveOperation", this.roomRequest)
        .then(() => {
          this.$vToastify.info({
            body: "Successfully reserved room for operation",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
          
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error while reserving operation",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
       
  }
}   
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>