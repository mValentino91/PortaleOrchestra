var indexCategories = 0;
var openedSlug = new Array();
var lastSelected = null;

function labelHandler(slug, id) {
    if ($(id).hasClass('in') && lastSelected === slug) {
        lastSelected = null;
    }
    else {
        triggerEvent(slug);
    }
}
function removeCategory(id, slug) {
    $(id).slideUp(150, function() {
        $(id).remove();
    });
    for (var i = 0; i < openedSlug.length; i++) {
        if (openedSlug[i] === slug) {
            delete openedSlug[i];
        }
    }
    if (lastSelected === slug) {
        lastSelected = null;
    }
}

function triggerEvent(slug) {
    lastSelected = slug;
    var catEvent = jQuery.Event('category_changed');
    catEvent.target = slug;
    $(document).trigger(catEvent);
}

function macroCategoryHandler(slug, title) {
    if (lastSelected !== slug) {
        triggerEvent(slug);
        var finded = false;
        for (var i = 0; i < openedSlug.length; i++) {
            if (openedSlug[i] === slug) {
                finded = true;
                break;
            }
        }
        if (!finded) {
            openedSlug.push(slug);
            document.getElementById('categoriesPanelGroup').innerHTML +=
                    '<div class="panel panel-default" style="display:none" id="categoryPanel-' + indexCategories + '">'
                    + '<div class="panel-heading">'
                    + '<a onclick="labelHandler(' + "'" + slug + "'" + ',' + "'#categoryCollapse-" + indexCategories + "'" + ')" class="' + slug + '" data-toggle="collapse" data-parent="#categoriesPanelGroup" href="#categoryCollapse-' + indexCategories + '">'
                    + title
                    + '</a>'
                    + '<button type="button" class="close"'
                    + 'onclick="removeCategory(' + "'" + '#categoryPanel-'
                    + indexCategories
                    + "'" + ',' + "'" + slug + "'" + ')">'
                    + '×</button>'
                    + '</div>'
                    + '<div id="categoryCollapse-' + indexCategories + '" class=" panel-collapse collapse out">'
                    + '<div class="panel-body">'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            $('#categoryPanel-' + indexCategories).show(150);
            $('.' + slug).trigger('click');
            indexCategories++;
        } else {
            $('.' + slug).trigger('click');
        }
    }
}


