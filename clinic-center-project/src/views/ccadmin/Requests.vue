<template>
  <div class="p-2 justify-content-center flex-row flex-wrap">
    <div class="d-flex p-2 justify-content-center flex-row flex-wrap">
      <div class="card mb-3" style="min-width: 250px; margin: 5px" v-for="request in filteredRequests" :key="request.id">
        <h3 class="card-header">{{request.firstName}} {{request.lastName}}</h3>
        <div class="card-body">
          <h6 class="card-subtitle text-muted">{{request.jmbg}}</h6>
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">{{request.email}}</li>
          <li class="list-group-item">{{request.address}}</li>
          <li class="list-group-item">{{request.city}}</li>
          <li class="list-group-item">{{request.country}}</li>
          <li class="list-group-item">{{request.telephone}}</li>
        </ul>
        <div class="card-body">
          <button type="button" class="btn btn-success"   v-on:click="accept(request)">
            Accept
          </button>
          <button
            type="button"
            class="btn btn-danger"
            data-toggle="modal"
            data-target="#exampleModal"
            data-whatever="@mdo"
            v-on:click="declineModal(request)"
          >
            Decline
          </button>
        </div>
      </div>
    </div>
      <div class="d-flex justify-content-center">
            <ul class="pagination">
            <!-- <li class="page-item disabled">
              <a class="page-link" href="#">&laquo;</a>
            </li>-->
            <li class="page-item active" v-for="i in pages" v-bind:key="i">
              <a class="page-link" v-on:click="nextPage(i - 1)">{{i}}</a>
            </li>
            <!-- <li class="page-item">
              <a class="page-link">&raquo;</a>
            </li>-->
          </ul>
          </div>
      <div
        class="modal fade"
        ref="modal"
        id="exampleModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Message</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form>
                <div class="form-group">
                  <textarea class="form-control" id="message-text" v-model="message"></textarea>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button
                type="button"
                class="btn btn-primary"
                data-dismiss="modal"
                v-on:click="decline()"
              >Send</button>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";
export default {
  name: "requests",
  data: function() {
    return {
        requests : [],
        response : undefined,
        request: {},
        message: undefined,
        pages: []
        
    };
  },
  mounted() {
    httpClient
      .get("/regrequest/all/0")
      .then(response => {
        this.requests = _.cloneDeep(response.data);
        this.filteredRequests = _.cloneDeep(response.data);
        this.pages = [];
        for (let i = 1; i <= this.requests[0].pages; i++) {
          this.pages[i - 1] = i;
        }
      })
      .catch(error => {
        this.error = error;
        alert(error);
      });
  },
  computed: {
    filteredRequests: function () {
      return this.requests.filter(function (r) {
        return r.verified === false || r.verified === null;
    })
     }
  },
  methods: {
    refresh: function() {
      httpClient
      .get("/regrequest/all/0")
      .then(response => {
        this.requests = _.cloneDeep(response.data);
        this.filteredRequests = _.cloneDeep(response.data);
        this.pages = [];
        for (let i = 1; i <= this.requests[0].pages; i++) {
          this.pages[i - 1] = i;
        }
      })
      .catch(error => {
        this.error = error;
        alert(error);
      });
    },
    nextPage: function(page) {
        httpClient
          .get("/regrequest/all/" + page)
          .then(response => {
            this.requests = _.cloneDeep(response.data);
            this.pages = [];
            if (this.requests[0].pages != undefined) {
              for (let i = 1; i <= this.requests[0].pages; i++) {
                this.pages[i - 1] = i;
              }
            }
          })
          .catch(error => {
            alert(error);
          });
    },
    accept: function(request) {
        httpClient
          .get("/mail/accept/"+request.email + "/"+ request.id)
          .then(response => {
            this.response = response.data;      
          })
          .catch(error => {
            this.error = error;
          });

        httpClient
          .put("/regrequest/accept/"+request.id)
          .then(response => {
            this.response = response.data;   
            this.refresh();   
          })
          .catch(error => {
            this.error = error;
          });
          this.$vToastify.success({
              body: "Mail has been sent",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
          });
    },
    decline: function() {
        if(this.message == undefined || this.message == ""){
          this.$vToastify.info({
              body: "Please, enter the reason for rejections",
              title: "Info",
              type: "info",
              canTimeout: true,
              append: false, duration: 2000
          });
          return;
        }
        httpClient
          .delete("/regrequest/"+this.request.id)
          .then(response => {
            this.response = response.data;   
            this.refresh();   
          })
          .catch(error => {
            this.error = error;
          });

        httpClient
          .get("/mail/refuse/"+this.request.email + "/" + this.message )
          .then(response => {
            this.response = response.data;      
          })
          .catch(error => {
            this.error = error;
          });
          this.message = undefined;

          this.$vToastify.success({
              body: "Mail has been sent",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
          });

          
    },
    declineModal: function(request) {
      this.request = request;
      this.message = undefined;
    }
  }
};
</script>
