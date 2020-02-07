<template>
    <div v-if="event.title === 'Examination'">
        <fieldset>
            <legend>Event Details</legend>
            <b>Start: </b>{{event.start}}<br>
            <b>Type: </b>{{examination.type.name}}<br>
            <b>Duration: </b>{{examination.duration/60000}} min<br>
            <b>Patient: </b>{{examination.patient.firstName}} {{examination.patient.lastName}}<br>
            <b>Room: </b>No. {{examination.examinationRoom.number}}<br>
        </fieldset>
        <button v-if="role === 'ROLE_DOCTOR'" class="btn btn-success" style="position: absolute; right: 0; bottom:0; margin: 35px" v-on:click="startExamination">Start examination</button>
    </div>
    <div v-else>
        <fieldset>
            <legend>Event Details</legend>
            <b>Start: </b>{{event.start}}<br>
            <b>Duration: </b>{{operation.duration/60000}} min<br>
            <b>Patient: </b>{{operation.patient.firstName}} {{operation.patient.lastName}}<br>
            <b>Room: </b>No. {{operation.operationRoom.number}}<br>
        </fieldset>
    </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
    props:{
        event:Object,
        id: undefined
    },
    data: function() {
        return {
            role: undefined,
            examination: undefined,
            operation: undefined,
        };
  },
  mounted(){
      this.role = localStorage.getItem('Authority');
      if(this.event.title === 'Operation'){
        httpClient
            .get("/operation/"+ this.id)
            .then(response => {
                this.operation = response.data;
            })
            .catch(error => {
                this.error = error;
            });
      }else if(this.event.title === 'Examination'){
        httpClient
            .get("/examination/"+ this.id)
            .then(response => {
                this.examination = response.data;
            })
            .catch(error => {
                this.error = error;
            });
      }
  },
  methods:{
      startExamination: function(){
          httpClient
            .get("/examination/check/"+ this.id)
                .then(() => {
                   this.$router.push('/doc/addexaminationreport/'+this.id);
                })
                .catch(() => {
                    this.$vToastify.info({
                    body: "Examination can't start, it's not time for that examination",
                    title: "Warning",
                    type: "warning",
                    canTimeout: true,
                    append: false, duration: 2000
                 });
                });
          
      }
  }
}
</script>