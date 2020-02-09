<template>
  <div>
    <div>
      <h3 align="center">List of doctor's today's appointments</h3>
    </div>
    <div class="d-flex p-2 justify-content-center">
      <table class="table table-hover" id="doctorsTable">
        <thead>
          <tr>
            <th scope="col">Date</th>
            <th scope="col">Start time</th>
            <th scope="col">Duration</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="e in examinations" :key="e.id" class="table-primary">
            <td>{{e.dateMoment}}</td>
            <td>{{e.appointment.startDate}}</td>
            <td>{{e.appointment.duration}} min</td>
          </tr>
        </tbody>
      </table>       
    </div>
    <div align="center">
        <h3 align="center">Select an appointment</h3>
        <br>
        
        <label for="text" class="p-2">Starting time of examination</label>
        <input type="time" class="p-2" id="time" name="time" v-model="time">
        <br>
        
        <label for="text" class="p-2">Duration</label>
        <select name="duration" id="duration" v-model="duration">
            <option value="15">15</option>
            <option value="20">20</option>
            <option value="30">30</option>
        </select>

        <br />
        <button class="btn btn-primary p-2" v-on:click.prevent="sendReq">Send request</button>
        <br />
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";
import moment from "moment";

export default {
    data: function() {
        return {
            // povezati tr-ove iz table
            examinations: [],
            hours: undefined,
            minutes: undefined,
            time: undefined,
            duration: undefined
        };
    },
    mounted() {
        let doctorID = this.$route.params.doctorID;
        let date = this.$route.params.date;

        // alert("DoctorID: " + doctorID + ", " + "date: " + date);

        httpClient
        .get("/examination/doctor/" + doctorID + "/" + date)
        .then(response => {
            if(response.data.length === 0)
                return;

            this.examinations = _.cloneDeep(response.data);

            for (var i = 0; i < this.examinations.length; i++) {
                //e.dateMoment = moment(e.date, moment.HTML5_FMT.DATE);
                //e.startDateMoment = moment(e.startDate, moment.HTML5_FMT.TIME);
                this.examinations[i].dateMoment = moment(this.examinations[i].date).format('LL');
                //this.examinations[i].startDateMoment = moment(this.examinations[i].startDate).format('LT');
                /*this.hours = this.examinations[i].appointment.startDate.getHours();
                this.minutes = this.examinations[i].appointment.startDate.getMinutes();
                this.examinations[i].startDateMoment = this.hours + ":" + this.minutes;*/
            }
        })
        .catch(error => {
            alert(error);
        });
    },
    methods: {
        sendReq: function() {
            if(!this.time || !this.duration){
                this.$vToastify.info({
                body: "Please, fill all the information",
                title: "Info",
                type: "info",
                canTimeout: true,
                append: false, duration: 2000
                });

                return;
            }

            let emailll = localStorage.getItem("Email");
            let doctorIDDD = this.$route.params.doctorID;
            let dateee = this.$route.params.date;

            /*
            httpClient
                .post("/patient/" + doctorID + "/" + email + "/" + this.time + "/" + this.duration + "/" + date)
                .then(response => {
                    this.response = response;
                })
                .catch(error => {
                     this.error = error;
                });
            */

           httpClient
                .post("/patient/saveExaminationRequest", {
                    date: dateee,
                    doctorID: doctorIDDD,
                    duration: this.duration,
                    email: emailll,
                    time: this.time
                })
                .then(response => {
                    this.response = response;  
                })
                    .catch(error => {
                    this.error = error;
                });
        }
    }
}
</script>