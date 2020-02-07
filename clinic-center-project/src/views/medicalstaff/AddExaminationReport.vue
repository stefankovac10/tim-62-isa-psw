<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="rewport" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Examination</h1>
      <br />
      <button
        type="button"
        class="btn btn-secondary m-2"
        data-toggle="modal"
        data-target="#scheduleNewAppointment"
        data-whatever="@mdo"
      >Schedule next appointment</button>
      <br />
      <div>
        <label class="typo__label">Diagnosis</label>
        <multiselect
          v-model="diagnosis"
          :options="diagnosisis"
          placeholder="Select one"
          label="name"
          track-by="name"
        ></multiselect>
      </div>
      <div class="form-group">
        <br />
        <label for="exampleTextarea">Report</label>
        <textarea class="form-control" id="exampleTextarea" rows="3" v-model="report"></textarea>
        <br />
        <div>
          <label class="typo__label">Medicines</label>
          <multiselect
            v-model="medicinesSelected"
            tag-placeholder="Add this as new tag"
            placeholder="Search or add a tag"
            label="name"
            track-by="code"
            :options="medicines"
            :multiple="true"
            :taggable="true"
          ></multiselect>
        </div>
        <br />
        <button class="btn btn-primary m-2" v-on:click.prevent="add()">Add</button>
      </div>
    </form>
    <div id="scheduleNewAppointment" class="modal">
      <div class="modal-dialog justify-content-center" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Schedule new appointment</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form id="scheduleNew" accept-charset="UTF-8" class="d-flex flex-column">
            <div class="modal-body d-flex flex-column">
              <div class="d-flex flex-column">
                <label for="type">Select appointment</label>
                <select name="type" id="typeEx" v-model="type">
                  <option value="examination">Examination</option>
                  <option value="operation">Operation</option>
                </select>
              </div>

              <label class="p-2">Date and time</label>
              <input type="datetime-local" class="p-2" id="start" name="start" v-model="start" />
              <label for="duration">Duration (in minutes)</label>
              <input type="number" name="duration" id="duration" v-model="duration" />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-primary"
                data-dismiss="modal"
                v-on:click.prevent="scheduleNew"
              >Submit</button>
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { httpClient } from "@/services/Api.js";
import Multiselect from "vue-multiselect";
// import moment from "moment";

export default {
  components: {
    Multiselect
  },
  data: function() {
    return {
      diagnosisis: [],
      diagnosis: {},
      medicines: [],
      medicinesSelected: [],
      report: undefined,
      examination: {
        prescription: {
          medication: []
        },
        type: undefined
      },
      type: undefined,
      start: undefined,
      patient: undefined,
      doctor: undefined,
      duration: undefined,
      id: undefined
    };
  },
  mounted() {
    this.id = this.$route.params.id;
    httpClient
      .get("/diagnosis/all")
      .then(response => {
        this.diagnosisis = response.data;
      })
      .catch(error => {
        this.error = error;
      });

    httpClient
      .get("/medication/all")
      .then(response => {
        this.medicines = response.data;
      })
      .catch(error => {
        this.error = error;
      });

    httpClient
      .get("/users/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.doctor = response.data;
      })
      .catch(error => {
        alert(error);
      });

    httpClient
      .get("/users/mail/giggs@gmail.com")
      .then(response => {
        this.patient = response.data;
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    add: function() {
      if (
        this.diagnosis === null ||
        this.report === undefined ||
        this.report === ""
      ) {
        this.$vToastify.warning({
          body: "Please, fill the report and chose the diagnosis",
          title: "Warning",
          type: "warning",
          canTimeout: true,
          append: false
        });
        return;
      } else {
        this.examination.report = this.report;
        this.examination.prescription.medication = this.medicinesSelected;
        this.examination.diagnosis = this.diagnosis;
        this.examination.id = this.id;

        httpClient
          .post("/examination/addReport", this.examination)
          .then(response => {
            this.response = response.data;
          })
          .catch(error => {
            this.error = error;
          });
        this.$vToastify.success({
          body: "Examination report has been saved",
          title: "Success",
          type: "success",
          canTimeout: true,
          append: false
        });
        this.$router.push("/doc/patients");
      }
    },
    scheduleNew: function() {
      let durationMinutes = this.duration * 60 * 1000;
      let appointment = {
        date: this.start,
        patientId: this.patient.id,
        doctorId: this.doctor.id,
        duration: durationMinutes
      };
      httpClient
        .post("/cadmin/scheduleNew/" + this.type, appointment)
        .then(() => {
          this.$vToastify.success({
            body: "Next appointment requested",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            successDuration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Could not create new appointment",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            errorDuration: 2000
          });
        });
    }
  }
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>


