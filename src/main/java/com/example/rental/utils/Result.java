package com.example.rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回结果
 *
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    /**
     * 结果集类，用于封装API调用的结果。
     * 包含调用结果的状态码、消息、成功标志和数据部分。
     *
     * @param <T> 结果数据的泛型类型，允许结果集返回不同类型的数据显示。
     */

    private Integer code; // 结果的状态码，通常表示操作的成功与否。
    private String message; // 结果的消息，用于描述操作的结果详情。
    private Boolean success; // 操作的成功标志，通过布尔值明确表示操作是否成功。
    private T data; // 操作的结果数据，包含调用API返回的具体数据。

    /**
     * 默认构造函数，用于创建一个空的结果集对象。
     */
    public Result() {
    }

    /**
     * 增，删，除，改，操作成功的方法
     * 成功结果集的创建方法。
     * <p>
     * 用于创建表示操作成功的Result对象，其中包含通用的成功信息。
     *
     * @param <T> 结果数据的泛型类型。
     * @return Result<T> 表示操作成功的结果对象。
     */
    public static <T> Result<T> success() {
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS).setMessage("操作成功");
        }

    /**
     * 带有具体数据的成功结果集的创建方法。
     * <p>
     * 用于创建表示操作成功的Result对象，其中包含具体的数据及成功信息。
     *
     * @param <T> 结果数据的泛型类型。
     * @param data 操作成功后返回的具体数据。
     * @return Result<T> 表示操作成功的结果对象，包含具体的数据。
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS).setMessage("操作成功").setData(data);
        }

    /**
     * 失败结果集的创建方法。
     * <p>
     * 用于创建表示操作失败的Result对象，其中包含通用的失败信息。
     *
     * @param <T> 结果数据的泛型类型。
     * @return Result<T> 表示操作失败的结果对象。
     */
    public static <T> Result<T> fail() {
        return new Result<T>().setSuccess(false)
                .setCode(ResultCode.ERROR).setMessage("操作失败");
        }
}

