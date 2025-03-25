package com.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Hello extends AbstractFunction {

    //自定义的变量，用于自定义函数调用时接收传入的参数
    public String username;
    public String sex;

    //函数名称，按默认使用双下划线开头
    private static final String key="__Hello";

    /**
     * <p>静态参数列表，用于存储初始化时的参数。</p>
     * <p>定义完成后由getArgumentDesc()返回</p>
     * <p>通过add方法可以添加一个参数，需要添加多个参数时，可以多次调用add方法。</p>
     */
    private static List<String> args = new LinkedList<>();
    static {
        //通过add方法可以添一个参数
        //需添加多个参数时，多次调用即可添加多个参数
        args.add("参数说明");
        args.add("参数2");
    }

    /**
     * 自定义函数的主体部分
     *
     * @param sampleResult 采样结果对象，用于存储测试结果
     * @param sampler 采样器对象，用于执行测试
     * @return 返回用户名和性别拼接的字符串，格式为"用户名__性别"
     * @throws InvalidVariableException 当变量无效时抛出异常
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        // 拼接用户名和性别，中间用"__"分隔
        return username + "__" + sex;
    }


    /**
     * 设置参数的方法，用于接收用户调用函数时传入的参数。
     *
     * @param collection 包含CompoundVariable对象的集合，其中第一个元素应为用户名，第二个元素应为性别。
     * @throws InvalidVariableException 如果参数无效，将抛出此异常。
     */
    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        // 将集合转换为数组以便于访问
        Object[] values = collection.toArray();

        // 提取并设置用户名
        username = ((CompoundVariable) values[0]).execute();
        // 提取并设置性别
        sex = ((CompoundVariable) values[1]).execute();
    }


    /**
     * 获取JMeter自定义函数的名称。
     *
     * @return 返回函数的名称，即key值。
     */
    @Override
    public String getReferenceKey() {
        return key;
    }


    /**
     * 获取函数参数描述列表。
     *
     * <p>该方法用于定义函数参数，并将参数描述显示在JMeter界面中。</p>
     *
     * @return 参数描述的列表
     */
    @Override
    public List<String> getArgumentDesc() {
        // 返回参数描述列表
        return args;
    }

}