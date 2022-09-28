<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="/include/head.jsp" %>
    <body class="d-flex flex-column h-100">
      <main class="flex-shrink-0">
            <%@ include file="/include/nav.jsp" %>
            <!-- Blog preview section-->
            <section class="py-5">
              <div class="container px-5 my-5">
                <div class="row gx-5 justify-content-center">
                  <div class="col-lg-8 col-xl-6">
                    <div style="display: flex; flex-direction: row">
                      <select
                        class="form-select sido-select"
                        id="dongbyeol-sido"
                        aria-label="Default select example"
                      >
                        <option selected disabled>시/도</option>
                      </select>
                      <select
                        class="form-select"
                        id="dongbyeol-gugun"
                        aria-label="Default select example"
                      >
                        <option selected disabled>시/구/군</option>
                      </select>
                      <select class="form-select" id="dongbyeol-dong" aria-label="Default select example">
                        <option selected disabled>동</option>
                      </select>
                      <button type="button" class="btn btn-primary" id = "appendBtn">관심 지역 추가</button>
                    </div>
                  </div>
                </div>
                <br />
                <div class="row gx-5">
                  <div style="display: flex; flex-direction: row; height : 600px">
                    <div style="flex-basis: 10%"></div>
                    <ul class="list-group list-group-flush" style="flex-basis: 20%">
                      <li class="list-group-item" style="font-size: 30px">관심 지역 목록</li>
                      
                      <div style="overflow-y:auto; height : 100%; white-space:nowrap;" id = "area-list">
      
                      </div>
                    </ul>
                    <div id="map" style="flex-basis: 60%; "></div>
                    <div style="flex-basis: 10%"></div>
                  </div>
                </div>
                <br /><br /><br /><br /><br /><br />
            </section>
          </main>
<%@ include file="/include/footer.jsp" %>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8c2ed21ab6956d39e256071885d15bfd"></script>
  <script>
    let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    let options = { //지도를 생성할 때 필요한 기본 옵션
      center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
      level: 5 //지도의 레벨(확대, 축소 정도)
    };

    let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    let sidoSelect = document.getElementsByClassName("sido-select");
    let appendBtn = document.getElementById("appendBtn");
    let areaList = document.getElementById("area-list");

    function moveMap(){
      let p = this.parentElement;

      let dongName = p.getAttribute("data-dongname");
      let sidoName = p.getAttribute("data-sidoname");
      let gugunName = p.getAttribute("data-gugunname");

      fetch(`http://localhost:8080/whereismyhome08_3/location/getcoordinate.do`,{
        method: "POST",
        headers: {
        "Content-Type": "application/json",
        },
        body: JSON.stringify({
          sidoName: sidoName,
          gugunName: gugunName,
          dongName: dongName
        })
      })
      .then((res) => res.json())
      .then((data) =>{
        let moveLatLon = new kakao.maps.LatLng(data[0]["lat"], data[0]["lng"]);
        map.panTo(moveLatLon);
      })
    }

    function removeArea(){
      let p = this.parentElement;

      let dongName = p.getAttribute("data-dongname");
      let sidoName = p.getAttribute("data-sidoname");
      let gugunName = p.getAttribute("data-gugunname");

      return fetch(`http://localhost:8080/whereismyhome08_3/area/gwansimDelete.do`,{
        method: "POST",
        headers: {
        "Content-Type": "application/json",
        },
        body: JSON.stringify({
          sidoName: sidoName,
          gugunName: gugunName,
          dongName: dongName
        })
      })
    }

    function getUserAreaList(){
      areaList.innerHTML = "";
      fetch(`http://localhost:8080/whereismyhome08_3/area/gwansimList.do`)
      .then((res) => res.json())
      .then((data) =>{
        for(let i = 0; i<data.length; i++){
          let userArea = document.createElement("div");
          let tdata = data[i];

          let dongName = tdata["dongName"];
          let gugunName = tdata["gugunName"];
          let sidoName = tdata["dongName"];
          userArea.setAttribute('data-sidoname',sidoName);
          userArea.setAttribute('data-gugunname',gugunName);
          userArea.setAttribute('data-dongname',dongName);

          userArea.innerHTML = `\${dongName} / \${gugunName} / \${sidoName}<br/>`;

          let changeMain = document.createElement("a");
          changeMain.innerText = "메인으로 변경";
          changeMain.addEventListener(moveMap);

          let deleteArea = document.createElement("a");
          deleteArea.innerText = "삭제";
          changeMain.addEventListener(removeArea);

          userArea.appendChild(changeMain);
          userArea.appendChild(deleteArea);
        }
      })
      
    }

    appendBtn.addEventListener("click",()=>{
      let dongSelectElement = document.getElementById("dongbyeol-dong");
      let dongName = dongSelectElement.options[dongSelectElement.selectedIndex].text;

      if(dongName == "동"){
        alert("관심 지역을 설정해 주세요");
        return;
      }
      
      let sidoSelectElement = document.getElementById("dongbyeol-sido");
      let gugunSelectElement = document.getElementById("dongbyeol-gugun");
      let gugunName = gugunSelectElement.options[gugunSelectElement.selectedIndex].text;
      let sidoName = sidoSelectElement.options[sidoSelectElement.selectedIndex].text;
      

      fetch(`http://localhost:8080/whereismyhome08_3/area/gwansimRegist.do`,{
        method: "POST",
        headers: {
        "Content-Type": "application/json",
        },
        body: JSON.stringify({
          sidoName: sidoName,
          gugunName: gugunName,
          dongName: dongName
        })
      })
      .then(getUserAreaList());
    })

    fetch("http://localhost:8080/whereismyhome08_3/location/getsido.do")
      .then((res) => res.json())
      .then((data) => {
        //console.log(data);
        for (let i = 0; i < sidoSelect.length; i++) {
          let idx = 0;
          for (key in data) {
            let opt = document.createElement("option");
            opt.value = idx++;
            opt.innerText = data[key];
            sidoSelect[i].appendChild(opt);
          }
        }
      });

    function sidoSelectOnChange(sidoId, gugunId, dongId) {
      let sidoSelectElement = document.getElementById(sidoId);
      let dongSelectElement = document.getElementById(dongId);
      
      sidoSelectElement.addEventListener("change", () => {
        let sidoName = sidoSelectElement.options[sidoSelectElement.selectedIndex].text;
        //console.log(sidoName);
        let gugunSelectElement = document.getElementById(gugunId);

        dongSelectElement.innerHTML = "<option selected disabled>동</option>";

        fetch("http://localhost:8080/whereismyhome08_3/location/getgugun.do?sidoName=" + sidoName)
          .then((res) => res.json())
          .then((data) => {
            //console.log(data);
            gugunSelectElement.innerHTML = "<option selected disabled>시/구/군</option>";
            let idx = 0;
            for (key in data) {
              let opt = document.createElement("option");
              //console.log("value : " + idx);
              opt.value = idx++;
              opt.innerText = data[key];
              gugunSelectElement.appendChild(opt);
            }
          });
      });
    }

    function gugunSelectOnChange(sidoId, gugunId, dongId) {
      let gugunSelectElement = document.getElementById(gugunId);
      let sidoSelectElement = document.getElementById(sidoId);
      gugunSelectElement.addEventListener("change", () => {
        let gugunName = gugunSelectElement.options[gugunSelectElement.selectedIndex].text;
        let sidoName = sidoSelectElement.options[sidoSelectElement.selectedIndex].text;
        //console.log(sidoName);
        let dongSelectElement = document.getElementById(dongId);
        fetch(
          `http://localhost:8080/whereismyhome08_3/location/getdong.do?gugunName=\${gugunName}&sidoName=\${sidoName}`
        )
          .then((res) => res.json())
          .then((data) => {
            //console.log(data);
            dongSelectElement.innerHTML = "<option selected disabled>동</option>";
            let idx = 0;
            for (key in data) {
              let opt = document.createElement("option");
              //console.log("value : " + idx);
              opt.value = idx++;
              opt.innerText = data[key];
              dongSelectElement.appendChild(opt);
            }
          });
      });
    }

    sidoSelectOnChange("dongbyeol-sido", "dongbyeol-gugun", "dongbyeol-dong");
    gugunSelectOnChange("dongbyeol-sido","dongbyeol-gugun", "dongbyeol-dong");
  </script>
    </body>
</html>