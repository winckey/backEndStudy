<template>
  <div id="map" class="map"></div>
</template>
<script>
export default {
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ed4e22a8176c7caffeb0b81caee3364d";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(this.addr.lat, this.addr.lon),
        level: 3,
      };

      var map2 = new kakao.maps.Map(container, options);

      var markerPosition = new kakao.maps.LatLng(this.addr.lat, this.addr.lon);
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map2);
    },
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 400px;
}
</style>
