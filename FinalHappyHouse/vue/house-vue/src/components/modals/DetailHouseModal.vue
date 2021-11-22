<template>
  <div class="modal" tabindex="-1" id="detailHouseModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">상세정보</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <img v-bind:src="this.$store.state.house.img">

          <table class="table table-hover">
            <tbody>
              




            </tbody>
          </table>
        </div>
        <div class="modal-footer">
        </div>
      </div>
    </div>
  </div>
</template>


<script>
  // 아래 import 사용 X
  // import http from "@/common/axios.js";
  // import util from "@/common/util.js";
  export default {
    name: 'DetailHouseModal',
    methods: {
    }
  }
</script>

<style>
</style>