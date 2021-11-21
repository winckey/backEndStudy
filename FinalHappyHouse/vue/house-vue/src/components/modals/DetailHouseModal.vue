<template>
  <div class="modal" tabindex="-1" id="detailModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">글 상세</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <table class="table table-hover">
            <tbody>
              <tr>
                <td>글번호</td> 
              </tr>
              <tr>
                <td>제목</td>
              
              </tr>
              <tr>
                <td>내용</td>
               
              </tr>
              <tr>
                <td>작성자</td>
         
              </tr>
              <!-- 아래 코드는 오류 발생 초기 생성 시점에 regDt = {} -->
              <!-- <tr><td>작성일시</td><td>{{ makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') }}</td></tr> -->
           
              <tr>

              </tr>
              <!-- / New for FileUpload -->
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
 

  export default {
    name: 'DetailModal',
    
    methods: {
     
      changeToUpdate() {
        this.$emit('call-parent-change-to-update');
        
      },
      changeToDelete() {
        this.$emit('call-parent-change-to-delete');

    
      }
    },

   


  }
</script>

<style>
</style>