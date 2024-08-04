import React, {useEffect, useState} from 'react';
import {PageContainer} from "@ant-design/pro-components";
import {
  getInterfaceInfoVoByIdUsingGet,
  invokeInterfaceInfoUsingPost
} from "@/services/kingapi-backend/interfaceInfoController";
import {Button, Card, Descriptions, Form, Input, message, Spin} from "antd";
import {useParams} from "@@/exports";

/**
 * 每个单独的卡片，为了复用样式抽成了组件
 * @param param0
 * @returns
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [resinvoke, setResinvoke] = useState<any>();
  const [invokeLoading, setinvokeLoading] = useState(false);
  const params = useParams();
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const loadData = async () => {
    if (!params.id) {
      message.error("参数不存在");
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoVoByIdUsingGet({
        id: Number(params.id),
      });
      setData(res.data);
    } catch (error: any) {
      message.error("请求失败", error.message);
    }
    setLoading(false);
  }
  useEffect(() => {
    loadData();
  }, [])
  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error("接口不存在")
    }
    setinvokeLoading(true)
    try {
      const res = await invokeInterfaceInfoUsingPost({
        id: params.id,
        ...values,
      })
      message.success("请求成功")
      setResinvoke(res.data);
      return true;
    } catch (error: any) {
      message.error("操作失败", error.message);
    }
    setinvokeLoading(false);
  }
  return (
    <PageContainer title="查看接口文档">
      <Card>
        {data ? (
          <Descriptions title={data?.name} column={1}>
            <Descriptions.Item label="状态">{data.status ? '开启' : '关闭'}</Descriptions.Item>
            <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
            <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
            <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="创建时间">
              {data.createTime}
            </Descriptions.Item>
            <Descriptions.Item label="更新时间">
              {data.updateTime}
            </Descriptions.Item>
          </Descriptions>
        ) : (<>接口不存在</>)

        }
      </Card>
      <Card title="在线测试">
        <Form
          name="invoke"
          layout="vertical"
          onFinish={onFinish}
        >
          <Form.Item
            label="请求参数"
            name="userRequestParams"
          >
            <Input.TextArea/>
          </Form.Item>
          <Form.Item wrapperCol={{span: 16}}>
            <Button type="primary" htmlType="submit">
              调用
            </Button>
          </Form.Item>
        </Form>

      </Card>
      <Card title="调用结果">
        <Spin spinning={invokeLoading}/>
        {resinvoke}
      </Card>
    </PageContainer>
  );
};

export default Index;
