(function(c, h, l, g) {
    var k = function(a, b) {
        this.$element = c(a);
        this._element = a;
        this._elementId = this._element.id;
        this._styleId = this._elementId + "-style";
        this.tree = [];
        this.nodes = [];
        this.selectedNode = {};
        this._init(b)
    };
    k.defaults = {injectStyle: !0, levels: 2, expandIcon: "glyphicon glyphicon-plus", collapseIcon: "glyphicon glyphicon-minus", nodeIcon: "glyphicon glyphicon-stop", color: g, backColor: g, borderColor: g, onhoverColor: "#F5F5F5", selectedColor: "#FFFFFF", selectedBackColor: "#428bca", enableLinks: !1, highlightSelected: !0,
        showBorder: !0, showTags: !1, onNodeSelected: g};
    k.prototype = {remove: function() {
            this._destroy();
            c.removeData(this, "plugin_treeview");
            c("#" + this._styleId).remove()
        }, _destroy: function() {
            this.initialized && (this.$wrapper.remove(), this.$wrapper = null, this._unsubscribeEvents());
            this.initialized = !1
        }, _init: function(a) {
            a.data && ("string" === typeof a.data && (a.data = c.parseJSON(a.data)), this.tree = c.extend(!0, [], a.data), delete a.data);
            this.options = c.extend({}, k.defaults, a);
            this._setInitialLevels(this.tree, 0);
            this._destroy();
            this._subscribeEvents();
            this._render()
        }, _unsubscribeEvents: function() {
            this.$element.off("click")
        }, _subscribeEvents: function() {
            this._unsubscribeEvents();
            this.$element.on("click", c.proxy(this._clickHandler, this));
            if ("function" === typeof this.options.onNodeSelected)
                this.$element.on("nodeSelected", this.options.onNodeSelected)
        }, _clickHandler: function(a) {
            this.options.enableLinks || a.preventDefault();
            var b = c(a.target);
            a = b.attr("class") ? b.attr("class").split(" ") : [];
            b = this._findNode(b);
            -1 != a.indexOf("click-expand") ||
                    -1 != a.indexOf("click-collapse") ? (this._toggleNodes(b), this._render(), -1 != a.indexOf("click-expand") && this._setSelectedNode(b), -1 != a.indexOf("click-collapse") && -1 != a.indexOf("node-selected") && this._setSelectedNode(b)) : b && this._setSelectedNode(b)
        }, _findNode: function(a) {
            a = a.closest("li.list-group-item").attr("data-nodeid");
            (a = this.nodes[a]) || console.log("Error: node does not exist");
            return a
        }, _triggerNodeSelectedEvent: function(a) {
            this.$element.trigger("nodeSelected", [c.extend(!0, {}, a)])
        }, _setSelectedNode: function(a) {
            a &&
                    (a === this.selectedNode ? this.selectedNode = {} : this._triggerNodeSelectedEvent(this.selectedNode = a), this._render())
        }, _setInitialLevels: function(a, b) {
            if (a) {
                b += 1;
                var d = this;
                c.each(a, function(a, c) {
                    b >= d.options.levels && d._toggleNodes(c);
                    var f = c.nodes ? c.nodes : c._nodes ? c._nodes : g;
                    if (f)
                        return d._setInitialLevels(f, b)
                })
            }
        }, _toggleNodes: function(a) {
            if (a.nodes || a._nodes)
                a.nodes ? (a._nodes = a.nodes, delete a.nodes) : (a.nodes = a._nodes, delete a._nodes)
        }, _render: function() {
            this.initialized || (this.$element.addClass("treeview"),
                    this.$wrapper = c(this._template.list), this._injectStyle(), this.initialized = !0);
            this.$element.empty().append(this.$wrapper.empty());
            this.nodes = [];
            this._buildTree(this.tree, 0)
        }, _buildTree: function(a, b) {
            if (a) {
                b += 1;
                var d = this;
                c.each(a, function(a, e) {
                    e.nodeId = d.nodes.length;
                    d.nodes.push(e);
                    for (var f = c(d._template.item).addClass("node-" + d._elementId).addClass(e === d.selectedNode ? "node-selected" : "").attr("data-nodeid", e.nodeId).attr("style", d._buildStyleOverride(e)), g = 0; g < b - 1; g++)
                        f.append(d._template.indent);
                    e._nodes ? f.addClass("click-expand") : e.nodes && f.addClass("click-collapse");
                    f.append(c(d._template.iconWrapper).append(c(d._template.icon).addClass(e.icon ? e.icon : d.options.nodeIcon)));
                    d.options.enableLinks ? f.append(c(d._template.link).attr("href", e.href).append(e.text)) : f.append(e.text);
                    e.slug && f.attr("slug", e.slug);
                    d.options.showTags && e.tags && c.each(e.tags, function(a, b) {
                        f.append(c(d._template.badge).append(b))
                    });
                    d.$wrapper.append(f);
                    if (e.nodes)
                        return d._buildTree(e.nodes, b)
                })
            }
        }, _buildStyleOverride: function(a) {
            var b =
                    "";
            this.options.highlightSelected && a === this.selectedNode ? b += "color:" + this.options.selectedColor + ";" : a.color && (b += "color:" + a.color + ";");
            this.options.highlightSelected && a === this.selectedNode ? b += "background-color:" + this.options.selectedBackColor + ";" : a.backColor && (b += "background-color:" + a.backColor + ";");
            return b
        }, _injectStyle: function() {
            this.options.injectStyle && !l.getElementById(this._styleId) && c('<style type="text/css" id="' + this._styleId + '"> ' + this._buildStyle() + " </style>").appendTo("head")
        }, _buildStyle: function() {
            var a =
                    ".node-" + this._elementId + "{";
            this.options.color && (a += "color:" + this.options.color + ";");
            this.options.backColor && (a += "background-color:" + this.options.backColor + ";");
            this.options.showBorder ? this.options.borderColor && (a += "border:1px solid " + this.options.borderColor + ";") : a += "border:none;";
            a += "}";
            this.options.onhoverColor && (a += ".node-" + this._elementId + ":hover{background-color:" + this.options.onhoverColor + ";}");
            return this._css + a
        }, _template: {list: '<ul class="list-group"></ul>', item: '<li class="list-group-item"></li>',
            indent: '<span class="indent"></span>', iconWrapper: '<span class="icon"></span>', icon: "<i></i>", link: '<a href="#" style="color:inherit;"></a>', badge: '<span class="badge"></span>'}, _css: ".list-group-item{cursor:pointer;}span.indent{margin-left:10px;margin-right:10px}span.icon{margin-right:5px}"};
    c.fn.treeview = function(a, b) {
        return this.each(function() {
            var d = c.data(this, "plugin_treeview");
            "string" === typeof a ? d ? c.isFunction(d[a]) && "_" !== a.charAt(0) ? ("string" === typeof b && (b = [b]), d[a].apply(d, b)) : h.console &&
                    h.console.error("No such method : " + a) : h.console && h.console.error("Not initialized, can not call method : " + a) : d ? d._init(a) : c.data(this, "plugin_treeview", new k(this, c.extend(!0, {}, a)))
        })
    }
})(jQuery, window, document);