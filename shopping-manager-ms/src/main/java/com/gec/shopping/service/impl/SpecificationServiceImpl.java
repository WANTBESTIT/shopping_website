package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbSpecificationMapper;
import com.gec.shopping.mapper.TbSpecificationOptionMapper;
import com.gec.shopping.pojo.TbSpecification;
import com.gec.shopping.pojo.TbSpecificationExample;
import com.gec.shopping.pojo.TbSpecificationOption;
import com.gec.shopping.pojo.TbSpecificationOptionExample;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.pojo.entity.Specification;
import com.gec.shopping.service.SpecificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<TbSpecification> findAllSpecification() {
        return tbSpecificationMapper.selectByExample(null);
    }

    /**
     * 增加
     */
    @Override
    public void addSpecification(Specification specification) {
        // 保存规格  一方的数据
        tbSpecificationMapper.insert(specification.getSpecification());
        System.out.println("多方的数据========"+specification.getSpecification().getId());
        System.out.println(specification.getSpecificationOptionList());
        // 保存规格选项  多方的数据
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            System.out.println(specificationOption.getOptionName());
            // 设置规格的ID:  主键回填
            specificationOption.setSpecId(specification.getSpecification().getId());

            specificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 修改
     *
     * @param specification
     */
    @Override
    public void updateSpecification(Specification specification) {
        // 修改规格 一方
        tbSpecificationMapper.updateByPrimaryKey(specification.getSpecification());

        // 先删除规格选项，在添加规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();

        criteria.andSpecIdEqualTo(specification.getSpecification().getId());

        specificationOptionMapper.deleteByExample(example);  //delete from specificatoin_option where spec_id=?

        // 保存规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            // 设置规格的ID:
            specificationOption.setSpecId(specification.getSpecification().getId());

            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void deleteSpecification(Long[] ids) {
        for (Long id : ids) {
            // 删除规格
            tbSpecificationMapper.deleteByPrimaryKey(id);

            // 删除规格选项:
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return tbSpecificationMapper.selectOptionList();
    }

    @Override
    public RestPage findPage(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public RestPage search(int pageNum, int pageSize, TbSpecification tbSpecification) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        if (tbSpecification.getSpecName() != null && tbSpecification.getSpecName().length() > 0) {
            criteria.andSpecNameLike("%" + tbSpecification.getSpecName() + "%");
        }
        Page<TbSpecification> page= (Page<TbSpecification>)tbSpecificationMapper.selectByExample(example);
        System.out.println("page.getResult()--------->"+page.getResult());
        System.out.println("page.getTotal()--------->"+page.getTotal());
        return new RestPage(page.getTotal(), page.getResult());
    }

    /**
     * 根据Id获取实体
     * @param id
     * @return
     */
    @Override
    public Specification findSpecificationById(Long id) {
        Specification specification = new Specification();
        // 根据规格ID查询规格对象
        TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);

        // 根据规格的ID查询规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> list = specificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptionList(list);
        return specification;
    }
}
