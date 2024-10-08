import {PageContainer} from '@ant-design/pro-components';
import {List, message} from 'antd';
import React, {useEffect, useState} from 'react';
import {listInterfaceInfoByPageUsingGet} from "@/services/kingapi-backend/interfaceInfoController";

/**
 * 每个单独的卡片，为了复用样式抽成了组件
 * @param param0
 * @returns
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = useState<number>(0);
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const loadData = async (current = 1, pageSize = 5) => {
    setLoading(true);
    try {
      const res = await listInterfaceInfoByPageUsingGet({
        current,
        pageSize,
      });
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (error: any) {
      message.error("请求失败", error.message);
    }
    setLoading(false);
  }
  useEffect(() => {
    loadData();
  }, [])
  return (
    <PageContainer title="接口开放平台">
      <List
        className="my-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => {
          const apiLink = `/interface_info/${item.id}`;
          return <List.Item
            actions={[<a key={item.id} href={apiLink}>查看</a>]}
          >
            <List.Item.Meta
              title={<a href={apiLink}>{item.name}</a>}
              description={item.description}
            />
            <div>content</div>
          </List.Item>
        }

        }
        pagination={
          {
            showTotal(total
                        :
                        number
            ) {
              return "总数: " + total;
            }
            ,
            pageSize: 5,
            total,
            onChange(page, pageSize) {
              loadData(page, pageSize);
            }
          }
        }
      />
    </PageContainer>
  )
}

export default Index;
