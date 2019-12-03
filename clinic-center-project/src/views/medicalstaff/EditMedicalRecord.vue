<template>
  <div class="d-flex p-2 justify-content-right">
    <form id="info" accept-charset="UTF-8" class="d-flex flex-column col-sm-2">
      <h1 class="p-2">Information</h1>

      <label class="p-2">Height</label>
      <input type="text" class="p-2" id="height" name="height" style = "width: 200px" v-model="height" v-bind:disabled=" mode == 'VIEW'"/>

      <label class="p-2">Width</label>
      <input type="text" class="p-2" id="width" name="width" v-model="width" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />

      <label class="p-2">Blood type</label>
      <input type="text" class="p-2" id="bloodType" name="bloodType" v-model="bloodType" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />
    
      <label class="p-2">Diopter </label>
      <input type="text" class="p-2" id="diopter" name="diopter" v-model="diopter" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'"/>

      <br>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'VIEW'" v-on:click.prevent="edit">Edit</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="save">Save</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="cancel">Cancel</button>
    </form>
    <div>
        <h1> History </h1> 
        <div class="overflow-auto" style="width: 350px; height: 580px; overflow-y: scroll;">       
            <div class="card border-success mb-3" style="max-width: 20rem;" v-for="examination in medicalRecord.examinations" v-bind:key="examination">
                    <div class="card-header">Dr. {{examination.doctor.lastName}}</div>
                    <div class="card-body">
                        <h4 class="card-title">{{examination.type}}</h4>
                        <p class="card-text">{{examination.report}}.</p>
                        <button type="button" class="btn btn-info" v-on:click="editReport(examination)">Edit</button>
                    </div>
            </div>
            <div class="card border-success mb-3" style="max-width: 20rem;">
                    <div class="card-header">Dr. Kovac</div>
                    <div class="card-body">
                        <h4 class="card-title">Ocni</h4>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <button type="button" class="btn btn-info">Edit</button>
                    </div>
            </div>
        </div>
    </div>
        
  </div>
  
</template>

<script>
export default {
  data: function() {
    return {
        height: undefined,
        width: undefined,
        bloodType: undefined,
        diopter: undefined,
        mode: 'VIEW',
        medicalRecord: {},
        report: undefined,
        examination: {}
    };
  },
  mounted(){

  },
  methods: {
    edit: function(){
        this.mode = 'EDIT';
    },
    save: function(){
        this.mode = 'VIEW';
    },
    cancel: function(){
        this.mode = 'VIEW';
    },
    editReport: function(examination){
        this.report = examination.report;
        this.examination = examination;
    }
  }
};
</script>
