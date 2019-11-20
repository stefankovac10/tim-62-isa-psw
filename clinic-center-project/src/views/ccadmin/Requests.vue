<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div class="card mb-3" style="min-width: 250px;">
      <h3 class="card-header">stefan10</h3>
      <div class="card-body">
        <h5 class="card-title">Stefan Kovac</h5>
        <h6 class="card-subtitle text-muted">1001997153952</h6>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item">stefankovac42@gmail.com</li>
        <li class="list-group-item">Nevesinje</li>
        <li class="list-group-item">Nevesinje</li>
        <li class="list-group-item">Republika Srpska</li>
        <li class="list-group-item">066880036</li>
      </ul>
      <div class="card-body">
        <button type="button" class="btn btn-success" v-on:click="accept">
          Accept
        </button>
        <button
          type="button"
          class="btn btn-danger"
          data-toggle="modal"
          data-target="#exampleModal"
          data-whatever="@mdo"
        >
          Decline
        </button>
      </div>
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
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <textarea class="form-control" id="message-text"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button type="button" class="btn btn-primary" v-on:click="decline">
              Send
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "requests",
  data: function() {
    return {
      mode: "VIEW"
    };
  },
  methods: {
    accept: function() {},
    decline: function() {}
  }
};
</script>
