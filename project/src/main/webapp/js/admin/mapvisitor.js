// // Khởi tạo bản đồ
// var map = L.map('map').setView([20, 0], 2);
//
// // Thêm lớp bản đồ
// L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
//     attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
// }).addTo(map);
//
// //lấy dữ liệu
// fetch(`${window.context}/admin/api/map`)
//     .then(response => response.json())
//     .then(data => {
//         let mapData = {};
//         data.data.forEach(entry => {
//             mapData[entry.country] = entry.count;
//         });
//
//         fetch(`${window.contextPath}/js/admin/world.geo.json`)
//             .then(response => response.json())
//             .then(geojson => {
//                 var geojsonLayer = L.geoJson(geojson, {
//                     style: function (feature) {
//                         var countryName = feature.properties.name;
//                         var value = mapData[countryName] || 0;
//                         return {
//                             fillColor: getColor(value),
//                             weight: 2,
//                             opacity: 1,
//                             color: 'white',
//                             dashArray: '3',
//                             fillOpacity: 0.7
//                         };
//                     }
//                 }).addTo(map);
//
//                 // Tự động căn chỉnh bản đồ để hiển thị toàn bộ dữ liệu GeoJSON
//                 var bounds = geojsonLayer.getBounds();
//                 map.fitBounds(bounds);
//             })
//             .catch(error => {
//                 console.error('Có lỗi xảy ra:', error);
//                 alert('Có lỗi xảy ra khi tải dữ liệu bản đồ.');
//             });
//
//         console.log("Lấy data:", mapData);
//     })
//     .catch(error => {
//         console.error('Có lỗi xảy ra khi tải dữ liệu map:', error);
//     });
//
// // Hàm để xác định màu sắc dựa trên giá trị
// function getColor(value) {
//     return value > 500 ? '#800026' :
//         value > 200 ? '#BD0026' :
//             value > 100 ? '#E31A1C' :
//                 value > 50  ? '#FC4E2A' :
//                     value > 20  ? '#FD8D3C' :
//                         value > 10  ? '#FEB24C' :
//                             value > 0   ? '#FED976' : '#FFEDA0';
// }

// import {da} from "../../libs/suneditor-master/src/lang";

(async () => {

    const topology = await fetch(
        'https://code.highcharts.com/mapdata/custom/world-lowres.topo.json'
    ).then(response => response.json());

    // Prepare demo data. The data is joined to map using value of 'hc-key'
    // property by default. See API docs for 'joinBy' for more info on linking
    // data and map.
    const data = await fetch(`${window.context}/admin/api/map`).then(response => response.json())
    console.log("data" +data.toString())
    // Create the chart
    Highcharts.mapChart('container', {
        chart: {
            map: topology
        },

        title: {
            text: 'Highcharts Maps basic demo'
        },

        subtitle: {
            text: 'Source map: <a href="https://code.highcharts.com/mapdata/custom/world-lowres.topo.json">World, low resolution</a>'
        },

        mapNavigation: {
            enabled: true,
            buttonOptions: {
                verticalAlign: 'bottom'
            }
        },

        colorAxis: {
            min: 0
        },

        series: [{
            data: data,
            name: 'Random data',
            states: {
                hover: {
                    color: '#BADA55'
                }
            },
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }]
    });

})();