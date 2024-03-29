package online.shixun.demo.elearning.mapper;

import java.util.List;
import java.util.Map;
import online.shixun.demo.elearning.dto.Slide;
import online.shixun.demo.elearning.dto.SlideExample.Criteria;
import online.shixun.demo.elearning.dto.SlideExample.Criterion;
import online.shixun.demo.elearning.dto.SlideExample;
import org.apache.ibatis.jdbc.SQL;

public class SlideSqlProvider {

    public String countByExample(SlideExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("so_slide");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SlideExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("so_slide");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Slide record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("so_slide");
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTypeCode() != null) {
            sql.VALUES("type_code", "#{typeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getReferenceId() != null) {
            sql.VALUES("reference_id", "#{referenceId,jdbcType=BIGINT}");
        }
        
        if (record.getReferenceUrl() != null) {
            sql.VALUES("reference_url", "#{referenceUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            sql.VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getImageSuffix() != null) {
            sql.VALUES("image_suffix", "#{imageSuffix,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SlideExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("create_time");
        } else {
            sql.SELECT("create_time");
        }
        sql.SELECT("update_time");
        sql.SELECT("id");
        sql.SELECT("type_code");
        sql.SELECT("reference_id");
        sql.SELECT("reference_url");
        sql.SELECT("title");
        sql.SELECT("brief");
        sql.SELECT("image_suffix");
        sql.FROM("so_slide");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Slide record = (Slide) parameter.get("record");
        SlideExample example = (SlideExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("so_slide");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getTypeCode() != null) {
            sql.SET("type_code = #{record.typeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getReferenceId() != null) {
            sql.SET("reference_id = #{record.referenceId,jdbcType=BIGINT}");
        }
        
        if (record.getReferenceUrl() != null) {
            sql.SET("reference_url = #{record.referenceUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        }
        
        if (record.getImageSuffix() != null) {
            sql.SET("image_suffix = #{record.imageSuffix,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("so_slide");
        
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("type_code = #{record.typeCode,jdbcType=VARCHAR}");
        sql.SET("reference_id = #{record.referenceId,jdbcType=BIGINT}");
        sql.SET("reference_url = #{record.referenceUrl,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        sql.SET("image_suffix = #{record.imageSuffix,jdbcType=VARCHAR}");
        
        SlideExample example = (SlideExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SlideExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}