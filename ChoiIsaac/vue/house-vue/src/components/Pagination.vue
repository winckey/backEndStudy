<template>
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <li v-if="prev" class="page-item">
        <a
          class="page-link"
          href="#"
          aria-label="Previous"
          @click="paginationChanged(startPageIndex - 1)"
        >
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li
        v-for="index in endPageIndex - startPageIndex + 1"
        :key="index"
        v-bind:class="{ active: startPageIndex + index - 1 == $store.state.board.currentPageIndex }"
        class="page-item"
      >
        <a @click="paginationChanged(startPageIndex + index - 1)" class="page-link" href="#">{{
          startPageIndex + index - 1
        }}</a>
        <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="next" class="page-item">
        <a
          class="page-link"
          href="#"
          aria-label="Next"
          @click="paginationChanged(endPageIndex + 1)"
        >
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Pagination",
  // props 사용 X
  // props: ['listRowCount', 'pageLinkCount', 'currentPageIndex', 'totalListItemCount'],
  data() {
    return {};
  },
  computed: {
    pageCount: function () {
      return this.$store.getters.getPageCount;
    },
    startPageIndex: function () {
      return this.$store.getters.getStartPageIndex;
    },
    endPageIndex: function () {
      return this.$store.getters.getEndPageIndex;
    },
    prev: function () {
      return this.$store.getters.getPrev;
    },
    next: function () {
      return this.$store.getters.getNext;
    },
  },
  methods: {
    // 부모에게 event 전달
    paginationChanged(pageIndex) {
      console.log("paginationVue : paginationChanged : pageIndex : " + pageIndex);
      this.$emit("call-parent", pageIndex);
    },
  },
};
</script>
<style>
a {
  text-decoration: none;
}

p,
li,
a {
  font-size: 14px;
}

/* GRID */

.twelve {
  width: 100%;
}
.eleven {
  width: 91.53%;
}
.ten {
  width: 83.06%;
}
.nine {
  width: 74.6%;
}
.eight {
  width: 66.13%;
}
.seven {
  width: 57.66%;
}
.six {
  width: 49.2%;
}
.five {
  width: 40.73%;
}
.four {
  width: 32.26%;
}
.three {
  width: 23.8%;
}
.two {
  width: 15.33%;
}
.one {
  width: 6.866%;
}

/* COLUMNS */

.col {
  display: block;
  float: left;
  margin: 1% 0 1% 1.6%;
}

.col:first-of-type {
  margin-left: 0;
}

/* GENERAL STYLES */

.pagination {
  padding: 30px 0;
}

.pagination ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
}

.pagination a {
  display: inline-block;
  padding: 10px 18px;
  color: #222;
}

/* ONE */

.p1 a {
  width: 40px;
  height: 40px;
  line-height: 40px;
  padding: 0;
  text-align: center;
}

.p1 a.is-active {
  background-color: #2ecc71;
  border-radius: 100%;
  color: #fff;
}

/* TWO */

.p2 .is-active li {
  font-weight: bold;
  border-bottom: 3px solid #2ecc71;
}

/* THREE */

.p3 .is-active {
  background-color: #2ecc71;
  color: #fff;
}

/* FOUR */

.p4 a {
  width: 40px;
  height: 40px;
  line-height: 40px;
  border-radius: 100%;
  padding: 0;
  text-align: center;
  position: relative;
  border: 3px solid #2ecc71;
}

.p4 .is-active:before {
  content: "";
  width: 30px;
  height: 30px;
  border-radius: 100%;
  background-color: #2ecc71;
  position: absolute;
  top: 5px;
  left: 5px;
}

/* FIVE */

.p5 a {
  width: 30px;
  height: 5px;
  padding: 0;
  margin: auto 5px;
  background-color: rgba(46, 204, 113, 0.4);
}

.p5 .is-active {
  background-color: #2ecc71;
}

/* SIX */

.p6 a {
  width: 30px;
  height: 30px;
  border-radius: 100%;
  padding: 0;
  margin: auto 5px;
  text-align: center;
  position: relative;
  background-color: rgba(46, 204, 113, 0.4);
}

.p6 .is-active {
  background-color: #2ecc71;
}

/* SEVEN */

.p7 a {
  border: 3px solid #2ecc71;
  margin: auto 5px;
  color: #2ecc71;
  font-weight: bold;
}

.p7 .is-active {
  background-color: #2ecc71;
  color: #fff;
}

/* EIGHT */

.p8 a {
  background-color: #2ecc71;
  margin: auto 5px;
  color: #fff;
  font-weight: bold;
  border: 3px solid #2ecc71;
}

.p8 .is-active {
  background-color: #fff;
  color: #2ecc71;
}

/* NINE */

.p9 a {
  width: 30px;
  height: 30px;
  line-height: 30px;
  padding: 0;
  text-align: center;
  margin: auto 5px;
}

.p9 a.is-active {
  border: 3px solid #2ecc71;
  border-radius: 100%;
}

/* TEN */

.p10 a {
  width: 30px;
  height: 30px;
  line-height: 30px;
  padding: 0;
  text-align: center;
  margin: auto 5px;
}

.p10 a.is-active {
  border: 3px solid #2ecc71;
}

/* ELEVEN */

.p11 a {
  background-color: #2ecc71;
  margin: auto 5px;
  color: #fff;
  border: 3px solid #2ecc71;
  position: relative;
}

.p11 a:first-of-type:before {
  content: "";
  position: absolute;
  top: -3px;
  left: -25px;
  border-top: 22px solid transparent;
  border-bottom: 22px solid transparent;
  border-right: 22px solid #2ecc71;
}

.p11 a:last-of-type:after {
  content: "";
  position: absolute;
  top: -3px;
  right: -25px;
  border-top: 22px solid transparent;
  border-bottom: 22px solid transparent;
  border-left: 22px solid #2ecc71;
}

.p11 .is-active {
  font-weight: bold;
}

/* TWELVE */

.p12 a:first-of-type,
.p12 a:last-of-type,
.p12 .is-active {
  background-color: #2ecc71;
  color: #fff;
  font-weight: bold;
}
</style>
