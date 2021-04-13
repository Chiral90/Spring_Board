<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Detail (Update)</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Update Page
                        </div>
                        <%-- <div><%=request.getParameter("no") %></div> --%>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action="/board/update" method="post">
                        		<!-- pageNum, amount를 받아오기 위해 추가 -->
                        		<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }" />'>
                        		<input type='hidden' name='amount' value='<c:out value="${cri.amount }" />'>
                        		<!-- 글 번호 -->
                        		<c:forEach items="${board }" var="board">
                        		<div class="form-group">
                        			<label>No</label><input class="form-control" name="no" 
                        			value='<c:out value="${board.no }" />' readonly="readonly">
                        		</div>
                        		<div class="form-group">
                        			<label>Title</label><input class="form-control" name="title"
                        			value="<c:out value='${board.title }' />">
                        		</div>
                        		<div class="form-group">
                        			<label>Text Area</label>
                        			<textarea class="form-control" rows="3" name="content"><c:out value='${board.content }' />
                        			</textarea>
                        		</div>
                        		<div class="form-group">
                        			<label>Writer</label><input class="form-control" name="writer"
                        			value="<c:out value='${board.writer }' />" readonly="readonly">
                        		</div>
                        		<div class="form-group">
                        			<label>RegDate</label>
                        			<input class="form-control" name="regdate" value='<c:out value="${board.regdate }" />' readonly="readonly">
                        		</div>
                        		<div class="form-group">
                        			<label>UpdateDate</label>
                        			<input class="form-control" name="updatedate" value='<c:out value="${board.updateDate }" />' readonly="readonly">
                        		</div>
                        		<button data-oper="update" class="btn btn-default"
                        		type="submit">Update(Modify)</button>
                        		<button data-oper="delete" class="btn btn-danger"
                        		type="submit">Delete</button>
                        		<button data-oper="list" class="btn btn-info"
                        		type="submit">List</button>
                        		</c:forEach>
                        	</form>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
            <!-- 버튼에 따라 다른 동작 할 수 있게 -->
            
            <script type="text/javascript">
            $(document).ready(function() {
            	var formObj = $("form");
            	
            	$('button').on("click", function(e) {
            		e.preventDefault();
            		
            		var operation = $(this).data("oper");
                	
                	console.log(operation);
                	
                	if (operation === 'delete') {
                		formObj.attr("action", "/board/delete");
                	} else if (operation === 'list') {
                		//move to list
                		//self.location = "/board/list" //
                		//form을 통한 이동
                		//list 버튼을 클릭할 경우 action속성과 method 속성을 변경
                		formObj.attr("action", "/board/list").attr("method", "get");
                		//"/board/list"로의 이동은 아무런 파라미터가 없기 때문에 form의 모든 내용은 삭제하고 submit()을 진행
                		formObj.empty();
                		//이후에 코드가 실행되지 않도록 return을 통해서 제어 ??? return 어디?
                	}
                	formObj.submit();
            	});
            });
            </script>
            
<%@ include file="../includes/footer.jsp" %>