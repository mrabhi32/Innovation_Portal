<s:if test="hasActionErrors()">
<script type="text/javascript">
$(function() {
	<s:iterator value="actionErrors">
	alertify.set({ delay: 10000 });
	alertify.error('<s:property escape="false" />');
	</s:iterator>	
	
});
</script>
</s:if>
<s:if test="hasFieldErrors()">
<script type="text/javascript">
$(function() {
	<s:iterator value="fieldErrors">
		<s:iterator value="value">
			alertify.set({ delay: 10000 });
			alertify.error('<s:property escape="false" />');
		</s:iterator>
	</s:iterator>
});
</script>
	</s:if>

<s:if test="hasActionMessages()">
<script type="text/javascript">
$(function() {
	<s:iterator value="actionMessages">
		alertify.set({ delay: 10000 });
		alertify.success('<s:property escape="false" />');
	</s:iterator>
});
    </script>
	</s:if>
