<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column">
      <h1>Vacation requests</h1>
      <div
        v-for="req in requests"
        v-bind:key="req.id"
        class="card border-primary mb-3 d-flex flex-row flex-wrap"
        style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
      >
        <div class="card-body" v-if="!req.accepted">
          <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
          <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4>
          <p class="card-text">From: {{req.startDate}}</p>
          <p class="card-text">To: {{req.endDate}}</p>

          <button type="button" class="btn btn-primary" v-on:click="accept(req)">Accept</button>
          <button
            type="button"
            class="btn btn-danger"
            data-toggle="modal"
            data-target="#refuseModal"
            data-whatever="@mdo"
            data-dismiss="modal"
            v-on:click="giveMeReason(req)"
          >Refuse</button>
        </div>
      </div>
    </div>
    <div id="refuseModal" class="modal">
      <div class="modal-dialog justify-content-center" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Refusing request</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
            <div class="modal-body d-flex flex-column">
              <label class="p-2">Please enter explanation for this refusal</label>
              <input type="text" class="p-2" id="reason" name="reason" v-model="reason" />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-primary"
                data-dismiss="modal"
                v-on:click.prevent="refuse"
              >Save changes</button>
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

export default {
  data: function() {
    return {
      requests: undefined,
      id: undefined,
      reason: undefined
    };
  },
  mounted() {
    httpClient
      .get("/vacs/all")
      .then(response => {
        this.requests = response.data;
      })
      .catch(error => {
        alert(error.data);
      });
  },
  methods: {
    accept: function(req) {
      httpClient.get("/mail/accept-vacation/balsa.smi15@gmail.com/" + req.id);
      this.$vToastify.info({
        body: "Mail is sent." ,
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
      });
      this.requests.splice(this.requests.indexOf(req), 1);
    },
    giveMeReason: function(req) {
      this.id = req.id;
      this.refuse();
    },
    refuse: function() {
      httpClient.get(
         "mail/refuse-vacation/balsa.smi15@gmail.com/" + this.id + this.reason
      );
      this.$vToastify.info({
        body: "Mail is sent." ,
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
      });

       

    }
  }
};
</script>