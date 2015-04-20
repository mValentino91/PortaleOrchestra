<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script>
$(document).on("loginDone",reload_filterOnPoiList);
function reload_filterOnPoiList(event){
   $.reloadElement("filterOnPoiList","./filterOnPoiList");
}
</script>

<!--<div  class="btn-group" role="group" data-toggle="buttons">
    <button type="button" class="btn btn-default btn-xs" 
            onclick="$('.categoriesContainer').toggle();">
        <i class="fa fa-bars"></i>
    </button>
</div>-->
<div  class="btn-group" role="group" data-toggle="buttons">
    <label id="searchMapCheckButton" type="button" class="btn btn-default btn-xs" onclick="interactiveMap.searchHandler()">
        <input type="checkbox" autocomplete="off">
        <i class="fa fa-search"></i>
    </label>
    <button type="button" class="btn btn-default btn-xs" onclick="interactiveMap.anmHandler()">
        <i class="fa fa-bus"></i>
    </button>
    <button type="button" class="btn btn-default btn-xs" onclick="$('#poiListComponent').slideToggle(150)">
        <i class="glyphicon glyphicon-map-marker"></i>
    </button>
    <sec:authorize access="hasRole('ROLE_FB')">
    <!--<button type="button" class="btn btn-default btn-xs" onclick="interactiveMap.showFbPois()">
        <i class="fa fa-facebook-f"></i>
    </button> -->
    </sec:authorize>
</div>