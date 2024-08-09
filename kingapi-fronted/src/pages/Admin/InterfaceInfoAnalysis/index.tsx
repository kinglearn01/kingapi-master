import '@umijs/max';
import React, {useEffect, useState} from 'react';
import ReactECharts from 'echarts-for-react';
import {PageContainer} from "@ant-design/pro-components";
import {listTopInvokeInterfaceInfoUsingGet} from "@/services/kingapi-backend/analysisController";

/**
 * 接口分析
 * @constructor
 */
const InterfaceAnalysis: React.FC = () => {
const [data,setData] = useState<API.InterfaceInfoVO[]>([]);
const [loading,setLoading] = useState(true);
useEffect(()=>{
    try {
listTopInvokeInterfaceInfoUsingGet().then(res=>{
  if (res.data){
     setData(res.data);
  }
})
    }catch (e:any){

    }
  },[])
  const chartData = data.map(item=>{
    return{
      value: item.totalNum,
      name:item.name,
    }
  });
  const options = {
    title: {
      text: '调用最多的接口',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: chartData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };
  return (
    <PageContainer>
      <ReactECharts loadingOption={{showLoading:loading}} option={options} />
    </PageContainer>
  );
};
export default InterfaceAnalysis;
