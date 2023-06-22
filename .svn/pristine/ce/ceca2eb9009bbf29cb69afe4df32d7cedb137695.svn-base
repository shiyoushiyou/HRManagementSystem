$(document).ready(function(){
    // 监听下拉菜单选项的选择事件
    $("#mySelect").change(function(){
        // 获取选择的选项值
        var selectedValue = $(this).val();
        $.ajax({
            // 发送请求
            url: "/hrms/getMonthData",
            // 请求方式
            type: "POST",
            // 请求参数
            data: {month: selectedValue},
            // 指定返回的数据类型
            dataType: "json"
        })
        // 处理成功返回的数据
        .done(function (data) {
            // 检查是否有数据
            if (data && data.length > 0) {
                // 根据选择的选项值生成相应的表格
                var table = "<table >\n"+
                    "<tr>\n"+
                    "<th>员工编号</th>\n"+
                    "<th>月份</th>\n"+
                    "<th>工资结算日期</th>\n"+
                    "<th>基本工资</th>\n"+
                    "<th>奖金</th>\n"+
                    "<th>津贴</th>\n"+
                    "<th>社保</th>\n"+
                    "<th>公积金</th>\n"+
                    "<th>个人所得税</th>\n"+
                    "<th>总工资</th>\n"+
                    "</tr>\n";
                // 遍历返回的数据生成表格内容
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    table += "<tr>\n"+
                        "<td>"+ item.employeeId +"</td>\n"+
                        "<td>"+ item.month +"</td>\n"+
                        "<td>"+ item.salaryDate1 +"</td>\n"+
                        "<td>"+ item.basicSalary +"</td>\n"+
                        "<td>"+ item.bonus +"</td>\n"+
                        "<td>"+ item.allowance +"</td>\n"+
                        "<td>"+ item.socialInsurance +"</td>\n"+
                        "<td>"+ item.housingFund +"</td>\n"+
                        "<td>"+ item.tax +"</td>\n"+
                        "<td>"+ item.totalSalary +"</td>\n"+
                        "</tr>\n";
                }
                table += "</table>";
                $("#myTable").html(table);

                // 生成下载按钮
                var btn = $('<button>', {
                    text: '下载PDF',
                    id: 'exportpdf',
                });
                $('#button-container').html(btn);

                // 点击导出PDF按钮
                $(document).ready(function() {
                    // 创建jsPDF对象
                    var doc = new jsPDF();
                    // 点击导出PDF按钮
                    $('#exportpdf').click(function() {
                        // 将HTML表格转换为canvas元素
                        html2canvas(document.querySelector("#myTable")).then(canvas => {
                            // 将canvas元素添加到PDF中
                            doc.addImage(canvas.toDataURL("image/png"), "PNG", 10, 10, 180, 0);
                            // 下载PDF文件
                            doc.save("table.pdf");
                        });
                    });
                });
            } else {
                // 没有数据，清空表格和下载按钮
                $("#myTable").html("");
                $("#button-container").html("");
            }
        })
        // 处理失败返回的数据
        .fail(function () {
            alert("请求数据失败！");
        });
    });
});